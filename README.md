# Program 2: Searching For Answers

## Description

This program uses the iterative algorithms discussed in Dr. William Kreahling's
CS351 course to perform Depth-First Search (DFS), Transitive Closure (TC), and 
cycle searches in directed graphs. Undirected graphs are not supported.


## Usage

Please see the following example output of this program:

```bash
    > java Driver graph.txt
        
        Please enter valid source and destination vertices >> 0 3

        [DFS Discovered Vertices: 0, 3] Vertex 0, Vertex 1, Vertex 2, Vertex 3

        [DFS Path: 0, 3] Vertex 0 -> Vertex 1 -> Vertex 2 -> Vertex 3

        [TC: New Edges] 4   1
                        4   6
                        0   2
                        ...
                        NOTE: Not all newly created edges shown here

        [Cycle]: Cycle detected.
```

## Known Issues

There are no known issues at this time.
