sokol ->

    Take an itemstack and check it for a key "tree" 
    and check if said key operates tree. If it does, it is an item,
    call more things on it. Persistence is very non-abstracted
    and platform dependent

yuukomponent ->
    Take an itemstack and check it for key "component id" 
    Use key to load components recursively.