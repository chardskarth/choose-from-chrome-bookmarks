# Open Chrome Bookmarks using [Choose](https://github.com/chipsenkbeil/choose) 


## What does this do?
TODO: Create gif screen recording

## Limitation

I might as well start with this... This coded very minimally, and its not yet battle tested. 
Right now it just works for my use case, but feel free to submit an MR.

Some gotchas:
1. [Install script]() is very simple. You just need to add the script in your path.
2. There's no confiration file.
	2. Chrome bookmarks path is hardcoded [here](https://github.com/chardskarth/choose-from-chrome-bookmarks/blob/a181c5c5e6a7ca9da388dd230f1c167301028079/bin/choose-from-chrome-bookmarks#L3)
	1. Logging is can be found at [~/.logs/](https://github.com/chardskarth/choose-from-chrome-bookmarks/blob/a181c5c5e6a7ca9da388dd230f1c167301028079/bin/choose-from-chrome-bookmarks#L4)
3. You can [add other source](#adding-other-source-to-search) for completion by sourcing a zsh script that contains functions `_choose_from_bookmarks__add_*` from lib path.


## Dependencies

The following tools are required to be installes
1. [kscript](https://github.com/holgerbrandl/kscript)
1. [choose-gui](https://github.com/chipsenkbeil/choose)


## Installation

```
# First, install the dependencies. (kscript, zsh and choose-gui)
git clone git@github.com:chardskarth/choose-from-chrome-bookmarks.git
# add `bin` directory in exported path
echo "PATH=\$PATH:<location of this repo>/bin" >> ~/.profile
```

## Usage
```
usage: choose-from-chrome-bookmarks <command>
  These are the available commands:

  refreshlist    Refresh a cached list of bookmarks
  triggerchoose	 Trigger choose
```

## Other improvements

### Adding a hotkey for this script... I use [skhd](https://www.google.com/search?q=skhd&oq=skhd&aqs=chrome..69i57j35i39l2j0i20i263i512j0i512l3j69i60.1471j0j9&sourceid=chrome&ie=UTF-8).
This is optional.
```
brew install skhd
echo "ctrl - return : choose-from-chrome-bookmarks triggerchoose &>> ~/.logs/choose_from_bookmarks.log" >> ~/.config/skhd/skhdrc
```

### Adding other source to search
I also use [gdrive cli](https://github.com/prasmussen/gdrive) to automatically search folders and files from specific gdrive folders.

1. Add a `.zsh` file in the <repository path>/libs. These are sourced upon invoking the `refreshlist` command.
2. Create functions that starts with `_choose_from_bookmarks__add_`. These functions are invoked and their response is put in the cached text thats displayed in "choose".


## Uninstalling
You can just remove this repository from $PATH



