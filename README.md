# viking-war

### Requirements

- JDK 8+
- Gradle

### Start

To start game you should use:
- on linux: `./vikingwar`
- on macos: `./vikingwar.command`
- on windows: `vikingwar.cmd`

### Arguments

You can use following arguments

- `-N=<vikings>` set count of vikings, where `<vikings>` number of vikings. Default value is 3
- `-map=<map.name>` - load map from resources, where `<map.name>` - is name of map. for example `map1.txt` 
- `-random=<islands>` generate a random map, where `<islands>` number of islands. `<islands>` - is optional, default is 15.

examples:
- `./vikingwar -map=map1.txt -N=4`
- `./vikingwar -random -N=4`
- `./vikingwar -random=999 -N=150`
