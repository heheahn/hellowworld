# ~/.bashrc: executed by bash(1) for non-login shells.
# see /usr/share/doc/bash/examples/startup-files (in the package bash-doc)
# for examples

export SVN_EDITOR=vim
# If not running interactively, don't do anything
[ -z "$PS1" ] && return

# don't put duplicate lines in the history. See bash(1) for more options
# don't overwrite GNU Midnight Commander's setting of `ignorespace'.
export HISTCONTROL=$HISTCONTROL${HISTCONTROL+,}ignoredups
# ... or force ignoredups and ignorespace
export HISTCONTROL=ignoreboth

# append to the history file, don't overwrite it
shopt -s histappend

# for setting history length see HISTSIZE and HISTFILESIZE in bash(1)

# check the window size after each command and, if necessary,
# update the values of LINES and COLUMNS.
shopt -s checkwinsize

# make less more friendly for non-text input files, see lesspipe(1)
[ -x /usr/bin/lesspipe ] && eval "$(SHELL=/bin/sh lesspipe)"

# set variable identifying the chroot you work in (used in the prompt below)
if [ -z "$debian_chroot" ] && [ -r /etc/debian_chroot ]; then
    debian_chroot=$(cat /etc/debian_chroot)
fi

# set a fancy prompt (non-color, unless we know we "want" color)
case "$TERM" in
    xterm-color) color_prompt=yes;;
esac

# uncomment for a colored prompt, if the terminal has the capability; turned
# off by default to not distract the user: the focus in a terminal window
# should be on the output of commands, not on the prompt
force_color_prompt=yes

if [ -n "$force_color_prompt" ]; then
    if [ -x /usr/bin/tput ] && tput setaf 1 >&/dev/null; then
	# We have color support; assume it's compliant with Ecma-48
	# (ISO/IEC-6429). (Lack of such support is extremely rare, and such
	# a case would tend to support setf rather than setaf.)
	color_prompt=yes
    else
	color_prompt=
    fi
fi


if [ "$color_prompt" = yes ]; then
  PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\[\033[01;31m\]$(__git_ps1)\[\033[00m\]\$ '
else
  PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w$(__git_ps1)\$ '
fi

#if [ "$color_prompt" = yes ]; then
    #PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$ '
#else
    #PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$ '
#fi
unset color_prompt force_color_prompt

# If this is an xterm set the title to user@host:dir
case "$TERM" in
xterm*|rxvt*)
    PS1="\[\e]0;${debian_chroot:+($debian_chroot)}\u@\h: \w\a\]$PS1"
    ;;
*)
    ;;
esac

# Alias definitions.
# You may want to put all your additions into a separate file like
# ~/.bash_aliases, instead of adding them here directly.
# See /usr/share/doc/bash-doc/examples in the bash-doc package.

if [ -f ~/.bash_aliases ]; then
    . ~/.bash_aliases
fi

# enable color support of ls and also add handy aliases
if [ -x /usr/bin/dircolors ]; then
    eval "`dircolors -b`"
    alias ls='ls --color=auto'
    #alias dir='dir --color=auto'
    #alias vdir='vdir --color=auto'

    #alias grep='grep --color=auto'
    #alias fgrep='fgrep --color=auto'
    #alias egrep='egrep --color=auto'
fi

# some more ls aliases
#alias ll='ls -l'
#alias la='ls -A'
#alias l='ls -CF'
#alias tprconf='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/tprconf'
#alias tgenlic='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/tgenlic'

#alias httpd='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/httpd -s'
#alias httpdd='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/httpd'
#alias pm='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/pm'

#alias bpm='LD_LIBRARY_PATH=~/turtle/branches/3.15.x/lib:/usr/turtle-3.15.1-heheahn/lib ~/turtle/branches/3.15.x/bin/pm'
#alias bhttpd='LD_LIBRARY_PATH=~/turtle/branches/3.15.x/lib:/usr/turtle-3.15.1-heheahn/lib ~/turtle/branches/3.15.x/bin/httpd -s'

#alias khs='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/killhttpd -s'
#alias killhttpd='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/killhttpd'
#alias kps='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/killpm -s'
#alias killpm='LD_LIBRARY_PATH=~/turtle/trunk/lib:/usr/turtle-heheahn/lib ~/turtle/trunk/bin/killpm'
alias st='git status'
alias mi='make install'
alias mc='make cleanall'
alias md='make CONFIG_LIB_DEBUG=y CONFIG_USER_DEBUG=y UOPT=" " CONFIG_INSTALL_NOSTRIP=y SHARED_LIB=no libxt_lall xmsd_ponly'
alias gdb105='gdb ~/ngsf/romfs/usr/local/6bin/xmsd'
alias ll='ls -al'
alias sshdel='rm /home/heheahn/.ssh/known_hosts'
alias dd64='sudo dd if=./dom64.img of=/dev/sdc'
#alias md='make debug'
alias ssh105='ssh -1 admin@10.200.3.105'
alias gvim='gvim -f'

# enable programmable completion features (you don't need to enable
# this, if it's already enabled in /etc/bash.bashrc and /etc/profile
# sources /etc/bash.bashrc).
if [ -f /etc/bash_completion ]; then
    . /etc/bash_completion
fi

export CDPATH=.:..:$HOME:$HOME/ngsf:$HOME/ngsf/COMPONENTS:$HOME/ngsf/COMPONENTS/management:$HOME/ngsf/COMPONENTS/management/xms:$HOME/turtle/trunk/src/webadmin:$HOME/turtle/trunk/src/pm:$HOME/turtle/trunk/src/webadmin/resources/js/tmpl/app:$HOME/turtle/trunk/src/webadmin/resources/js/:
#export PATH=$PATH:$HOME/turtle/trunk/bin:

# set a fancy prompt (non-color, unless we know we "want" color)
case "$TERM" in
  xterm-color) color_prompt=yes;;
esac

if [ -n "$SCHROOT_SESSION_ID" ]; then
  USER=$SCHROOT_USER
  TERM=xterm
  export USER TERM
  export debian_chroot=$SCHROOT_SESSION_ID
fi

# uncomment for a colored prompt, if the terminal has the capability; turned
# off by default to not distract the user: the focus in a terminal window
# should be on the output of commands, not on the prompt
force_color_prompt=yes

RTE_SDK=/opt/6WIND/contrib/473333_DPDK.L.1.1.0_27-v1.7
RTE_TARGET=x86_64-default-linuxapp-gcc
PATH=/opt/6WIND/contrib/toolchain/gcc-4.4.6-eglibc-2.15/x86_64-eglibc_2_15_gcc_4_4_6-linux-gnu/bin:$PATH
export PATH RTE_SDK RTE_TARGET
