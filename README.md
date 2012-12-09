ColorWater
==========

Minecraft mod to test metadata sensitive liquids.

How to compile
--------------

You need to prepare
* MCP development environment with MinecraftForge
* BuildCraft API
* Forestry for Minecraft API

If you use <code>ant</code> for build, you need to put BC and FFM API into <code>%MCPROOT%/api/</code>
and write <code>build.properties</code> file, then run <code>ant release</code> in the project directory.
This <code>build.xml</code> has not been fully tested.

This is an example directory structure:

<pre>
%MCPROOT%
    |- api/
    |- bin/
    |- forge/
    |- jars/
    |- reobf/
    |- other MCP files and dirs
    |- ColorWater/
        |- src/
        |- resources/
        |- .classpath
        |- .gitignore
        |- .project
        |- build.properties
        |- build.xml
</pre>

And an example <code>build.properties</code>:
<pre>
dir.mcp=../
dir.project=${dir.mcp}/ColorWater
dir.src=${dir.project}/src
dir.resource=${dir.project}/resources
dir.release=${dir.project}/release
release.mod.version=0.0.0
release.mod.name=colorwater-universal
</pre>