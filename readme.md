# YuuKomponent
A component behavior modelling system

# YuuKomponent-API
A set of constants (As in classes, not state) used to design YuuKomponent design implementations

# YuuKomponent-Core
Staging ground for a synchronous YuuKomponent core implementation

# Dictionary

##Required Features
- [] Behavior loading
- [x] Component modellingn 
- [] Component persistence
- [] Component loading
- [x] Stat calculation
- [] Stat definitions
- [] Stat definition storage persistence

##The new behaviour oriented architecture

YuuKomponent's core:
    Components
    Slots
    Behaviors
    Plugins
    Configuration (It is possible that all behaviors will have "config" raw values. From here we load stat definitions.)

YuuKomponent's inbuilt modules:
    Stats (implemented as behavior)
    Persistents (implemented as behavior)
    Structures (implemented as plugin)

##Event handling in order of importance
 1. (Component Level) Hardcoded event overrides such as SlotChangeEvents
 2. (Behavior Level) Reserved behaviors such as the built in Stat and Persistent behaviors
 3. (Behavior Level) User behaviors
 4. (Behavior Level) Forced insertion behaviors (These are behaviors defined by a plugin that are inserted into every component that exists. DO not use these if you do not have a reason you could tell the yuukomponent developer and not have him mock you)