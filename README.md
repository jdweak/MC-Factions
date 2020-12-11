# MC-Factions
Minecraft plugin with basic factions implementation and custom commands

*This project was made for my Boy Scout Troop to play on during the lockdowns due to COVID. Each patrol was given a faction through the plugin.*

Overview: Players in the server belong to factions. When joining the server for the first time, players are given a custom item that lets them choose one of four factions. Factions can claim and steal land based on their strength, as well as set home locations to teleport to. Players cannot interact with blocks in the claimed land of other factions.

Commands:

chunkClaimInfo: displays information on who owns the chunk the command sender is in

claim: claims a chunk for the command sender's faction

unclaim: unclaims the chunk the command sender is in

leaveFaction: removes command sender from their current faction

setHome: sets the command sender's current location as their home

home: teleports the command sender to their home location

setFactionHome: sets the command sender's current location as their faction's home location

fHome: teleports the command sender to their faction's home location

Admin Commands:

giveFactionBook: gives command sender the custom faction choosing item

setFactionOwner: sets a player as the leader of a faction. Faction leaders must be assigned manually by admins. (Syntax is command, faction, player name)

setSpawnChunk: sets the current chunk as an area where all non admins cannot interact with blocks

removeSpawnChunk: removes current chunk from the spawn chunks list

customEnchant: allows the enchanting of items ignoring vanilla level caps and item compatibility (Syntax is command, enchantment, level)

give_custom_item_prototype: gives the player a teleport staff

*Note: information that keeps track of faction members, chunks, and home locations are wiped if the server is reloaded*
