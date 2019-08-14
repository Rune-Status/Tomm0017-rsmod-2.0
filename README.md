## What is RS Mod v2.0?
This project is a predecessor to https://github.com/Tomm0017/rsmod.

RS Mod has the goal of being a modular server where developers can easily
create, remove or even extend existing content.

## Why is this v2.0?
There was a lot of feedback received when RS Mod was made public, and I highly 
appreciate all of it. I have respect for a lot of constructive criticism that
was given, the only issue is that a lot of it was embedded in the core of the 
project. 

That's where I had to make a decision to either rewrite the core on the existing
RS Mod platform, or rework it from the ground up. The changes made to the core 
would be the same as what is being made in this project, so either way there 
was going to be an adaption phase even if we stuck (or stick) with the original
RS Mod.

The time spent on rewriting the code would be far greater than just writing it
from scratch due to how young RS Mod is as a whole (which is why it's been in
pre-release stages).

At the end of the day, I'm not only trying to contribute back to a community I 
feel I owe a debt to, but also trying to learn and improve myself along the way.

After weighing out the pros and cons of this new 'version' of RS Mod, I without
a doubt thought this was the best decision. I apologize to whoever doesn't feel 
this was and would be happy to discuss it in private!

## What makes this different than the original RS Mod?
As previously stated, we received a bit amount of feedback from respectable 
developers when RS Mod was made public. Some things include:
- Server running on two coroutine dispatchers
    - One for game-thread jobs
    - One for IO jobs
- Using Dependency Injection
- Extensibility
    - Though RS Mod v1.0 had the idea of making it easy for developers to 
    create or modify content, it lacked a template for making each plugin
    extensible to the point that additions, or removals, to the code could
    be made without even having to touch the original plugin packaging
- Sticking to an appropriate naming format
    - For example: Obj (item), Loc (object), packet names, etc
- Type-safe packet definitions
    - RS Mod v1.0 uses yaml without type-safety for packet classes

This version of the project will not have all these features made to perfection 
from the start. A lot of these ideas are something that will be criticised and
improved as time goes on. This is a learning experience for myself, and also
a learning experience for anyone in our community who *wants* to learn! 

## What will happen to RS Mod (v1.0)?
Whether RS Mod will be overwritten by this project, archived or have its ownership 
transferred is something I have not yet completely thought out.