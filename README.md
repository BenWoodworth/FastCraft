[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/BenWoodworth/FastCraft?include_prereleases)](https://github.com/BenWoodworth/FastCraft/releases)
[![CircleCI](https://img.shields.io/circleci/build/github/BenWoodworth/FastCraft/master)](https://circleci.com/gh/BenWoodworth/FastCraft/)
[![Downloads](https://img.shields.io/github/downloads/BenWoodworth/FastCraft/total.svg)](https://github.com/BenWoodworth/FastCraft/releases)
[![Donate](https://img.shields.io/badge/Donate-PayPal-yellow)](https://paypal.me/BenWoodworth)

Looking for FastCraft v2? Click [here](https://github.com/BenWoodworth/FastCraft/tree/v2/dev)!

# FastCraft
FastCraft is a Minecraft server plugin that changes the
way players craft their items.
With FastCraft, players can craft items faster than ever!
Instead of the usual 3x3 crafting grid, players will be shown
the FastCraft user interface, which shows all of the items
that can be crafted from the items within their inventory.
To craft an item, all they have to do is click on an item in
the GUI, and the item will be crafted automatically!

<p align="center">
    <img src="assets/demo-centered.png" alt="FastCraft GUI" />
</p>

## Server Requirements
- Java 8+
- Bukkit 1.7.5 - 1.15+

## Configuration
A configuration has not yet been implemented

## Commands
Commands have not yet been implemented

## Permissions
| Permission    | Description           |
|---------------|-----------------------|
| fastcraft.use | Use the FastCraft GUI |

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
