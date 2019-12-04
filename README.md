[![Downloads](https://img.shields.io/github/downloads/BenWoodworth/FastCraft/total.svg)](https://github.com/BenWoodworth/FastCraft/releases)

[FastCraft Builds](http://benwoodworth.net/projects/fastcraft/builds.html)

# FastCraft v2 [![CircleCI](https://circleci.com/gh/BenWoodworth/FastCraft/tree/v2%2Fstable.svg?style=svg)](https://circleci.com/gh/BenWoodworth/FastCraft/tree/v2%2Fstable)
Looking for FastCraft v2? [Click here!](https://github.com/BenWoodworth/FastCraft/tree/v2/dev)

# FastCraft v3 [![CircleCI](https://circleci.com/gh/BenWoodworth/FastCraft/tree/master.svg?style=svg)](https://circleci.com/gh/BenWoodworth/FastCraft/tree/master)
FastCraft v3 is being developed from the ground up in
[Kotlin](http://kotlinlang.org/) for both
[Bukkit](https://dev.bukkit.org/) and
[Sponge](https://www.spongepowered.org/),
and in so that the same plugin Jar will run seamlessly on either platform.

## Building
#### Requirements
- Java JDK 8
- Git

#### Clone the repo
```
git clone https://github.com/BenWoodworth/FastCraft.git
cd FastCraft
```

#### Choose a FastCraft Version
List available versions:
```
git tag --sort=-v:refname --column

```

Replace `<VERSION>` with the desired version:
```
git checkout tags/<VERSION>
```

#### Build

| OS            | Command             |
|---------------|---------------------|
| Linux / macOS | `./gradlew build`   |
| Windows       | `gradlew.bat build` |

#### Output
The newly built plugin jar will be in the `build/libs/` folder.
