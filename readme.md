# YuuKomponent
A component behavior modelling system

# YuuKomponent-API
A set of constants (As in classes, not state) used to design YuuKomponent design implementations

# YuuKomponent-Core
Staging ground for a synchronous YuuKomponent core implementation

# Dictionary
- **Base Features**: A feature set consisting of everything integral to what YuuKomponent is: Components, Structure, Behavior, Plugins, and Configuration.
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
- 
## Required Features
- [?] Behavior loading
- [x] Component modellingn 
- [?] Component persistence
- [] Component loading
- [x] Stat calculation
- [] Stat definitions
- [] Stat definition storage persistence

## Event handling in order of importance
 1. (Component Level) Hardcoded event overrides such as SlotChangeEvents
 2. (Behavior Level) Reserved behaviors such as the built in Stat and Persistent behaviors
 3. (Behavior Level) User behaviors
 4. (Behavior Level) Forced insertion behaviors (These are behaviors defined by a plugin that are inserted into every component that exists. DO not use these if you do not have a reason you could tell the yuukomponent developer and not have him mock you)