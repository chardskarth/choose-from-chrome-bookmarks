# Open Chrome Bookmarks using [Choose](https://github.com/chipsenkbeil/choose) 


## What does this do?
TODO: Create gif screen recording


## Dependencies

The following tools are required to be installes
1. [kscript](https://github.com/holgerbrandl/kscript)
1. [choose-gui](https://github.com/chipsenkbeil/choose)
1. [skhd](https://www.google.com/search?q=skhd&oq=skhd&aqs=chrome..69i57j35i39l2j0i20i263i512j0i512l3j69i60.1471j0j9&sourceid=chrome&ie=UTF-8). This is optional. You can use a different tool to trigger the script


## Installation

```
# First, install the dependencies. (kscript, zsh and choose-gui)
git clone git@github.com:chardskarth/choose-from-chrome-bookmarks.git
# add `bin` directory in exported path
echo "PATH=\$PATH:<location of this repo>/bin" >> ~/.profile
```


### Then adding a hotkey for it... I use skhd for this.
```
brew install skhd
echo "ctrl - return : choose-from-chrome-bookmarks triggerchoose &>> ~/.logs/choose_from_bookmarks.log" >> ~/.config/skhd/skhdrc
```

## Uninstalling
You can remove this repository from $PATH


## Limitation

This coded very minimally, and its not yet battle tested. 
Right now it just works for my use case, but feel free to submit an MR.


Some gotchas:
1. [Install script]() is very simple, it just creates a symlink to `~/.localscripts`
2. Chrome bookmarks path is hard [here]()
1. Logging is can be found at [~/.logs/]()
2. There's no configuration yet, you'll have to manually [configure paths here]()


