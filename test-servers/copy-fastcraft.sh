#! /bin/sh
# Copies "build/libs/FastCraft*.jar" to the test servers.

# Bukkit Servers
for d in test-servers/bukkit/*/
do
    mkdir -p "$d"/plugins/update/
    rm -f "$d"/plugins/update/FastCraft*.jar
    cp build/libs/FastCraft*.jar "$d"/plugins/update/FastCraft.jar
done

# Sponge Servers
for d in test-servers/sponge/*/
do
    mkdir -p "$d"/mods/plugins/
    rm -f "$d"/mods/plugins/update/FastCraft*.jar
    cp build/libs/FastCraft*.jar "$d"/mods/plugins/FastCraft.jar
done
