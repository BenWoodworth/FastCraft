[![CircleCI](https://img.shields.io/circleci/build/github/BenWoodworth/FastCraft/master)](https://circleci.com/gh/BenWoodworth/FastCraft/)
[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/BenWoodworth/FastCraft?include_prereleases)](https://github.com/BenWoodworth/FastCraft/releases)
[![Downloads](https://img.shields.io/github/downloads/BenWoodworth/FastCraft/total.svg)](https://github.com/BenWoodworth/FastCraft/releases)
[![Donate](https://img.shields.io/badge/Donate-PayPal-yellow)](https://paypal.me/BenWoodworth)

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
