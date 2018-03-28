# ExtremeSportsSOS
Simple android app, that detects when you are not moving for longer period of time (like: you had an accident and are now unconscious) and sends an SOS message with your coordinates to defined contact

[![Build status](https://build.appcenter.ms/v0.1/apps/72b8b850-1584-4239-956c-5d2cf33d138f/branches/master/badge)](https://appcenter.ms)

## Why?
This project was created to show how to use [clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) in not-so-trivial example. Developed in [Kotlin](kotlinlang.org), using [MVP](https://en.wikipedia.org/wiki/Model–view–presenter) for presentation and repository pattern for data access. There is also a little bit of [RxJava2](https://github.com/ReactiveX/RxJava) where it made sense to use it. [Dagger2](https://github.com/google/dagger) was used for dependency injection.  
  
This is by no means a "reference implementation" that rigorously follows all the rules. It's rather a real-life example with pragmatic approach.

## Future plans
At this point app is stable and usable, but there is still a LOT to do here (better code coverage, add more features, make the app not ugly, use all these fancy libraries and concepts). You can check what I'm up to by checking [issues](https://github.com/zeyomir/ExtremeSportsSOS/issues).
