# OBJECTIVE // KILL SOKOL

# YuuKomponent
A component behavior modelling system

# Dictionary
- **Base/Core Features**: A feature set consisting of everything integral to what YuuKomponent is: Components, Structure, Behavior, Plugins, and Configuration.
- **Inbuilt Features**: A feature set consisting of everything that can technically be implemented as Plugin features but are in practice coupled to the Base: Stats and Persistence.
- **Platform**: The application that YuuKomponent is attempting to provide functionality to, such as Spigot or perhaps a standalone game.
- **Components**: The central object or YuuKomponent, a persistent hierarchical object that has various Behaviors that let it interact with the Platform.
- **Behaviors**: Features that a Component has: Something that can take external data signalled to it and perform an interaction with the platform.
- **Structure**: The ability to save state related to how a component tree is represented (e.g. what the component's ID is, what children it currently holds, what slots it has)
- **Persistence**: The ability to save state directly coupled to a component
- **Plugin**: Something providing extended functionality to YuuKomponent, such as more Behavior definitions or a Nexus
- **Nexus**: Collection point for behavior signals, typically provides functionalities to other behaviors
- **Configuration**: Arbitrary user-assiged values attached to a Component that provide information to a Behavior on how to function. Typically, Configuration provides information on how Stats should be built.
- **Stats**: A behavior + nexus implementation of a recursive Component tree search that combines children Nodes' Configuration values to provide a compound value for use by relevant behaviors.
- **Persistents**: A behavior + nexus implementation of Persistence
- **Slot**: TODO
- **Tag**: TODO
- **Completion**: TODO
## Required Features

Status NO means not implemented yet, WIP is work in progress, and DONE is completed experimentally. ALPHA / BETA mean implemented for alpha or beta release but such terms will not be used for a long time since yuukomponent is early stage.

| Status | Feature Name | Description |
| --- | --- | --- |
| WIP | Behavior Loading | The ability to load behaviors from a registry |
| NO | Behavior Insertion | Core part of how YuuKomponent will be extensible, the ability for external addons to insert new behaviors to the registry safely and checkly |
| DONE | Component Modelling | Core design of how components will relay behavior and structure change to children |
| WIP | Component Loading | The ability for components to be loaded from various sources, including: Configuration Definition, Unordered Persistent Data (PDC), Manual plugin insertion |
| NO | Abstract Components | The ability for configuration definition to specify abstract components which are incomplete but can have behavior and configuration values specified and inherited |
| NO(TBA) | Component Inheritance | The ability for *fully functional* components to be inherited from by another component. Adds a second layer of depth to configuration, where a component is either expected to inherit functionality from relevant child nodes or is based off an existing functionality but as a separate derivative (e.g. not a child) | 
| NO | Custom Component Loading | The ability for base component type to be modified such to accept structurally separate components such as one with a LIFO queue of children to support exotic features like magazines |
| DONE | Stat Calculation | The ability for the inbuilt stat feature to calculate stats down the stat tree. |
| WIP | Stat Definitions | The ability for stats to be loaded from configuration definition values |
| NO | Persistent Structure | The ability for the structure of a component (identifier, type, and children) to be saved to an external or platform hooking data storage structure |
| NO | Persistent Values | Inbuilt feature where values that can be saved to an external or platform-hooking data storage structure |
| NO | Internal Documentation | High quality javadoc comments. Currently about 5% of the code is documented and the code comments are mostly satirical jokes. |
| NO | External Documentation | Wiki.js site for newb developers who don't read javadocs and for platform owners who want to make components |

## Event handling in order of importance
 1. (Component Level) Hardcoded event overrides such as SlotChangeEvents
 2. (Behavior Level) Reserved behaviors such as the built in Stat and Persistent behaviors
 3. (Behavior Level) User behaviors
 4. (Behavior Level) Forced insertion behaviors (These are behaviors defined by a plugin that are inserted into every component that exists. DO not use these if you do not have a reason you could tell the yuukomponent developer and not have him mock you)