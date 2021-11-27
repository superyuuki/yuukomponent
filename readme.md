# YuuKomponent

**YuuKomponent** is an item framework that aims to solve the chore
of writing custom item plugins which are persistent, modular, fast to write and fast to configure, and fit a user's exact needs.


How do you (as a minecraft server owner) use it?

1. Download YuuKomponent to your server's plugins folder
2. Install a behavior addonContext to YuuKomponent's addons folder to add features
3. Write a file.yml containing the items you want to invent to the definitions folder
4. Reload the addonContext

# Features

Some cool features that yuukomponent allows you to implement are
- **Truly** custom items that aren't bound to a single feature the developer adds
- Custom items with attachments
- Persistent items which save over restarts
- Ease of use for developers so additional item features are as easy as two classes
- Generators which can create user-defined trees of modular components with randomizer included

# Example

A modular custom item with reusable behavior is as simple as

```yaml
Shotgun:
  Values:
    Name: 'Shotgun'
    ProjectileDamage: +5 # base damage of 5
    ProjectileSpeed: +10
    MaxAmmo: +6
  Tags:
    - Gun
  Behaviors:
    - GunBehavior
  Slots:
    - SingleComponentSlot<Bullet> 
    - SingleComponentSlot<Scope>
```

## Contents

- [Quick Start](#quick-start)
  - [Replicating Other Plugins](#replicating-other-plugins)
    - [Replicating CrackShot](#replicating-crackshot)
- [Developer Quick Start](#developer-quick-start)
  - [Writing a Behavior](#writing-a-behavior)
- [Minimalism](#minimalism)

## Quick Start

YuuKomponent depends on
- Java >= 11
- [Paper]() >= 1.13.2*
- [Sponge]() (TODO)

YuuKomponent comes with no addons installed for spigot besides YuuCommons which only provides simple features.

You can get more addons [here](please host a hangar server).

## Replicating Other Plugins

These following diagrams show one way to replicate existing plugins within YuuKomponent.

### Replicating Crackshot

*This is all out of date since there is now a significant intention to shift every feature to a module and every item feature to a behavior: slots and tags will be behaviors.

Here is YuuKomponent acting as a gun addonContext.

In your components file

```yaml
#Asault rifle
AssaultRifle:
  Values:
    Name: 'AssaultRifle'
    #Item name is assault rifle
    ProjectileSpeed: +10
    #Shoots bullets at 10 m/s. Property demanded by GunBehavior
  Tags:
    - Gun #Fits in a gun slot (what holds a gun?)
    - Butts #Tags can be any string you want. They only define whether something fits in a slot or not.
  Behaviors:
    - GunBehavior
  Slots:
    - SingleComponentSlot<Scope> #Can have a scope
    - LockedSingleComponentSlot<Perk> #Can have a perk

#Scope that zooms you in 2x
2xScope:
  Values:
    ProjectileSpeed: x1.1 #Scoping makes bullets faster (?) inherited from parent
    ScopeAmount: +2 #Increases screen zoom by 2. Property demanded by ScopeBehavior
    ChangeItemDisplay: IRON_HOE #sets item to an iron hoe which using texturepacks looks like a scope. 
  Tags:
    - Scope #needed so we can attach it to a scope slot
  Behaviors:
    - ScopeBehavior
    - ItemDisplayBehavior #changes the displayed item to a scope
  
#Perk that gives people fire at a 50% chance
HellfirePerk:
  Values:
    - Chance: 50% #Has a 50% chance to cause fire damage. Property demanded by ChanceDependantBehavior
    - FlameDuration: +50 #burn for 50 seconds. Demanded by FlamingDamageBehavior.
  Tags:
    - Perk
  Behaviors:
    - ChanceDependantBehavior
    - FlamingDamageBehavior

#Perk that gives a potion effect of poison
VenomousPerk:
  Values:
    Potion: POISON
  Tags:
    - Perk
  Behaviors:
    - PotionDamageBehavior
  
```

In your blueprints file

```yaml

AssaultRifleGenerator:
  Base: AssaultRifle #generates the base component as AssaultRifle defined in Components
  Orders:
    - ThenInsert: 2xScope
    - ThenInsertRandom: (50>HellfirePerk, 50>VenomousPerk)

```

What does this do?

- We have an assault rifle which launches bullets at a velocity. It can have the following attached to it: A scope and a perk
- We have a 2x scope which fits the Scope slot. The scope will zoom in by 200% (+2) on a right click.
- We have a perk which can only be inserted by Blueprints.
  - There is a Hellfire perk with a 50% chance the target will be lit on fire for 50 seconds.
  - There is a Poisonous perk that poisons the target for the default value of 1 minute.

Players could be given one of each of these components and combine them themselves, but that would be 
tedious.

Let's take a look at the Blueprint.

The blueprints hold a Blueprint script called AssaultRifleGenerator. When this generator is activated, the following happens.

- An assault rifle is created
- A scope and corresponding bullets are inserted
- A perk is chosen randomly between the two existing perks and given to the gun.

Let's say we want a pistol, which only has perks and bullets, but we want to reuse the
existing perks we designed and spawn them with different chances.

Into your components file
```yaml

Pistol:
  Values:
    Name: Pistol
    ProjectileSpeed: +5
  Tags:
    - Gun
  Behaviors:
    - GunBehavior
  Slots:
    - LockedSingleComponentSlot<Magazine>
    - LockedSingleComponentSlot<Perk>
    
BoomPerk:
  Values:
    ExplosionRadius: 5
  Tags:
    - Perk
  Behaviors:
    - ExplosionDamageBehavior
```

Into your blueprints

```yaml

MyCustomPistolGenerator:
  Base: Pistol
  Orders:
    - ThenInsert: 30RoundMag
    - ThenInsertRandom: (33>HellfirePerk, 33>VenomousPerk, 34>BoomPerk)

```

Now we have a pistol, which only has a perk and mag (no scope) that cannot be modified by the user.

When the custom pistol generator is called, a new pistol is made that chooses randomly between any of our existing perks and the new perk we just made.

## Developer Quick Start

Import the API into your Maven project into the file pom.xml

Do this by adding a block like so

```xml

<dependencies>
  <dependency>
    <groupId>com.superyuuki</groupId>
    <artifactId>yuukomponent-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>

```

In order for this to load you will need to depend on our repository

Add the following block

```xml
<repositories>
  <repository>
    <id>yuukomponent</id>
    <url>https://maven.pkg.github.com/superyuuki/yuukomponent</url>
  </repository>
</repositories>
```

After this you should be good to go.

## Writing a behavior

To make a behavior you need a behavior source, the actual behavior, and a behavior loader if you want it to work with configs (which you do).

You will need to insert these things into YuuKomponent via an addonContext or a addonContext.

todo

## Minimalism

**YuuKomponent** is minimalist compared to other item plugins. This means:

- We try not include any features you do not want by default.
  - All additional features are opt in addons
  - Only configure what you need to configure
  - Remove features at any time
- Code is lightweight
  - Values are cached
  - Async where possible
  - You will not see 100 protobuf serializations / second here
- Code is well designed
  - Design is object oriented and logical (no mAGiCal reflection)
  - Code is very modular and well abstracted
- Written by a competent developer
  - Easy for intermediate devs to add stuff*
    - Depends on what you consider intermediate probably
  - Contributions always welcome
  - No shitty bukkit libraries
- Support is not ran by a powertripping nerd*
  - *not yet
  - Won't scream at you to "read the docs"
  - Won't complain if you make an "obvious" mistake
  - You can ping me
