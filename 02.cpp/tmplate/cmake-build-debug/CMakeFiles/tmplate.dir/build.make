# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = E:\DevelopTool\CLion-2017.2.1\bin\cmake\bin\cmake.exe

# The command to remove a file.
RM = E:\DevelopTool\CLion-2017.2.1\bin\cmake\bin\cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = E:\code\cpp_tutorials\02.cpp\tmplate

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/tmplate.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/tmplate.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/tmplate.dir/flags.make

CMakeFiles/tmplate.dir/main.cpp.obj: CMakeFiles/tmplate.dir/flags.make
CMakeFiles/tmplate.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/tmplate.dir/main.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\tmplate.dir\main.cpp.obj -c E:\code\cpp_tutorials\02.cpp\tmplate\main.cpp

CMakeFiles/tmplate.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/tmplate.dir/main.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E E:\code\cpp_tutorials\02.cpp\tmplate\main.cpp > CMakeFiles\tmplate.dir\main.cpp.i

CMakeFiles/tmplate.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/tmplate.dir/main.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S E:\code\cpp_tutorials\02.cpp\tmplate\main.cpp -o CMakeFiles\tmplate.dir\main.cpp.s

CMakeFiles/tmplate.dir/main.cpp.obj.requires:

.PHONY : CMakeFiles/tmplate.dir/main.cpp.obj.requires

CMakeFiles/tmplate.dir/main.cpp.obj.provides: CMakeFiles/tmplate.dir/main.cpp.obj.requires
	$(MAKE) -f CMakeFiles\tmplate.dir\build.make CMakeFiles/tmplate.dir/main.cpp.obj.provides.build
.PHONY : CMakeFiles/tmplate.dir/main.cpp.obj.provides

CMakeFiles/tmplate.dir/main.cpp.obj.provides.build: CMakeFiles/tmplate.dir/main.cpp.obj


# Object files for target tmplate
tmplate_OBJECTS = \
"CMakeFiles/tmplate.dir/main.cpp.obj"

# External object files for target tmplate
tmplate_EXTERNAL_OBJECTS =

tmplate.exe: CMakeFiles/tmplate.dir/main.cpp.obj
tmplate.exe: CMakeFiles/tmplate.dir/build.make
tmplate.exe: CMakeFiles/tmplate.dir/linklibs.rsp
tmplate.exe: CMakeFiles/tmplate.dir/objects1.rsp
tmplate.exe: CMakeFiles/tmplate.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable tmplate.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\tmplate.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/tmplate.dir/build: tmplate.exe

.PHONY : CMakeFiles/tmplate.dir/build

CMakeFiles/tmplate.dir/requires: CMakeFiles/tmplate.dir/main.cpp.obj.requires

.PHONY : CMakeFiles/tmplate.dir/requires

CMakeFiles/tmplate.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\tmplate.dir\cmake_clean.cmake
.PHONY : CMakeFiles/tmplate.dir/clean

CMakeFiles/tmplate.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" E:\code\cpp_tutorials\02.cpp\tmplate E:\code\cpp_tutorials\02.cpp\tmplate E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug E:\code\cpp_tutorials\02.cpp\tmplate\cmake-build-debug\CMakeFiles\tmplate.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/tmplate.dir/depend
