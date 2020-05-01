[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/BenWoodworth/FastCraft?include_prereleases)](https://github.com/BenWoodworth/FastCraft/releases)
[![Downloads](https://img.shields.io/github/downloads/BenWoodworth/FastCraft/total.svg)](https://github.com/BenWoodworth/FastCraft/releases)
[![Donate](https://img.shields.io/badge/stats-Bukkit-blue)](https://bstats.org/plugin/bukkit/FastCraft/1381)
[![Donate](https://img.shields.io/badge/donate-PayPal-yellow)](https://paypal.me/BenWoodworth)

Looking for FastCraft v2? Click [here](https://github.com/BenWoodworth/FastCraft/tree/v2/master)!

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
    <img src="assets/interface.png" alt="FastCraft GUI" />
</p>

## Server Requirements
- Java 8+
- Bukkit 1.7.5 - 1.15+

## Configuration
A configuration has not yet been implemented

## Commands
| Command                             | Permission                          | Description                           |
|-------------------------------------|-------------------------------------|---------------------------------------|
| `/fastcraft set enabled`            | fastcraft.command.set.enabled       | Enable/disable FastCraft for crafting |
| `/fastcraft set enabled * <player>` | fastcraft.admin.command.set.enabled | Enable/disable FastCraft for a player |
| `/fastcraft craft fastcraft`        | fastcraft.command.craft.fastcraft   | Open FastCraft                        |
| `/fastcraft craft grid`             | fastcraft.command.craft.grid        | Open a crafting grid                  |
| `/fastcraft craft * <player>`       | fastcraft.admin.command.craft       | Open crafting for a player            |

## Permissions
| Permission                          | Description                         |
|-------------------------------------|-------------------------------------|
| fastcraft.use                       | Use FastCraft for crafting          |
| fastcraft.command.set.enabled       | `/fastcraft set enabled`            |
| fastcraft.command.craft.grid        | `/fastcraft craft grid`             |
| fastcraft.command.craft.fastcraft   | `/fastcraft craft fastcraft`        |
| fastcraft.admin.command.set.enabled | `/fastcraft set enabled * <player>` |
| fastcraft.admin.command.craft       | `/fastcraft craft * <player>`       |

## Building FastCraft
A guide on how to build FastCraft can be found [here](https://github.com/BenWoodworth/FastCraft/wiki/Building-FastCraft).
