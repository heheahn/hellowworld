set nocompatible
call pathogen#helptags()
"call pathogen#runtime_append_all_bundles()
call pathogen#infect()
let mapleader = ","
nmap <silent> <leader>ev :e $MYVIMRC<CR>
nmap <silent> <leader>sv :so $MYVIMRC<CR>
set hidden
set nowrap        " don't wrap lines
set tabstop=2     " a tab is four spaces
set softtabstop=2     " a tab is four spaces
set backspace=indent,eol,start " allow backspacing over everything in insert mode
set autoindent    " always set autoindenting on
set copyindent    " copy the previous indentation on autoindenting
set number        " always show line numbers
set shiftwidth=2  " number of spaces to use for autoindenting
set shiftround    " use multiple of shiftwidth when indenting with '<' and '>'
set showmatch     " set show matching parenthesis
set ignorecase    " ignore case when searching
set smartcase     " ignore case if search pattern is all lowercase,
                  "    case-sensitive otherwise
set smarttab      " insert tabs on the start of a line according to
                  "    shiftwidth, not tabstop
set hlsearch      " highlight search terms
set incsearch     " show search matches as you type
set history=1000         " remember more commands and search history
set undolevels=1000      " use many muchos levels of undo
set wildignore=*.swp,*.bak,*.pyc,*.class
set title                " change the terminal's title
set visualbell           " don't beep
set noerrorbells         " don't beep
set nobackup
set noswapfile
set expandtab
syntax on
filetype on
filetype plugin on
filetype plugin indent on
autocmd filetype python setlocal sw=2 sts=2 et
autocmd filetype javascript setlocal sw=2 sts=2 et
set list listchars=tab:»›,trail:·
"set listchars=tab:>.,trail:.,extends:#,nbsp:.
"autocmd filetype html,xml set listchars-=tab:>.
set pastetoggle=<F2>
set mouse=a
nnoremap ; :
nnoremap <silent> <F5> :let _s=@/<Bar>:%s/\s\+$//e<Bar>:let @/=_s<Bar>:nohl<CR>
cmap w!! w !sudo tee % >/dev/null
map! <S-Insert> <MiddleMouse>
set guifont=Terminus\ 10
set cursorline
set t_Co=256
colorscheme xoria256
set autochdir

set statusline+=%#warningmsg#
set statusline+=%{SyntasticStatuslineFlag()}
set statusline+=%*

"let g:syntastic_always_populate_loc_list = 1
"let g:syntastic_auto_loc_list = 1
let g:syntastic_check_on_open = 1
let g:syntastic_check_on_wq = 0
    let g:syntastic_quiet_messages = {
        \ "!level":  "errors",
        \ "type":    "style",
        \ "regex":   '\m\[E111\]' }


set encoding=utf-8
set scrolloff=3
set showmode
set showcmd
set wildmenu
set wildmode=list:longest
set ttyfast
set ruler
set laststatus=2

map <silent> <leader>c : !cd ~/nexgfw/trunk/components/apps/web2py;make test;cd ~/nexgfw/trunk/work/rfs/usr/local;cp ~/nexgfw/trunk/components/libs/libdncrypto/dn_crypto.py ~/nexgfw/trunk/work/rfs/usr/local/web2py/applications/nexgfw/modules/;tar cvf a.tar web2py;mv a.tar ~/share<CR>

"if &cp | set nocp | endif
"set nocp
"let s:cpo_save=&cpo
"let OmniCpp_NamespaceSearch = 2
"set tags=./.TAGS,./.tags,~/tags/cpptags
"nmap gx <Plug>NetrwBrowseX
"nnoremap <silent> <Plug>NetrwBrowseX :call netrw#NetBrowseX(expand("<cWORD>"),0)
"let &cpo=s:cpo_save
"unlet s:cpo_save
"runtime! ftplugin/man.vim
"set autochdir
"set background=dark
"set fileencodings=ucs-bom,utf-8,default,euc-kr,latin1
"set nobomb
"set guifont=Terminus\ Bitstream\ Vera\ Sans\ Mono\ 9
"set helplang=ko
"set history=50
"set nomodeline
"set mouse=a
"set printoptions=paper:a4
"set ruler
"set runtimepath=~/.vim,/usr/share/vim/vimfiles,/usr/share/vim/vim72,/var/lib/vim/addons/after,~/.vim/after,~/turtle/trunk/include/turtle,~/turtle/trunk/src/pm,~/turtle/trunk/src/webadmin
"set path=.,~/.vim/tags/cpp_src
"set shiftwidth=2
"set suffixes=.bak,~,.swp,.o,.info,.aux,.log,.dvi,.bbl,.blg,.brf,.cb,.ind,.idx,.ilg,.inx,.out,.toc
"set termencoding=utf-8
"set viminfo='20,\"50
"set incsearch
"set nu
"set smarttab
"set expandtab
"set smartcase
"set shiftround
"set def=^\\s*#\\s*define
"set inc=^\\s*#\\s*include
"set tag=./tags,~/.vim/tags/cpp,~/.vim/tags/gl,
"set tags+=~/.vim/tags/c
"set tags+=~/.vim/tags/cpp
"set tags+=~/.vim/tags/gl
"set cscopetag
"set csprg=cscope
"set scr=10
"set nf=octal,hex
"set cinoptions=>s:0=s(0W4g0hsi2st0
"set cindent
"set ignorecase
"set fen
"set foldlevel=2
"set dir=~/.vim.tmp
"set backupdir=~/.vim.backup
"set hls
"set list listchars=tab:»›,trail:·
"set makeprg=(cd\ `git\ rev-parse\ --show-toplevel`;make\ xmsd_pmake)
"autocmd FileType c,idl,cpp,java,php,xml autocmd BufWritePre <buffer> :call setline(1,map(getline(1,"$"),'substitute(v:val,"\\s\\+$","","")'))
"set color=murphy
" vim: set ft=vim :
"autocmd BufWritePre * :%s/\s\+$//e
"func! Sts()
  "let st = expand("<cword>")
  "exe "sts ".st
"endfunc
"nmap ,st : call Sts() <cr>

"set textwidth=120
" turn syntax highlighting on
"set t_Co=256
"colorscheme wormbat
" turn line numbers on
"set number
" highlight matching braces
"set showmatch
" intelligent comments
"set comments=sl:/*,mb:\ *,elx:\ */
let g:DoxygenToolkit_authorName="Gerhard Gappmeier <gerhard.gappmeier@ascolab.com>"

" recreate tags file with 8F
"map <C-F8> :!ctags -R --c++-kinds=+pl --fields=+iaS --extra=+q .<CR>
"map <C-F12> :!make;make install .<CR>
"map <C-F11> :!cd ~/turtle/trunk/src/webadmin/resources;make install .<CR>
" create doxygen comment
map <F7> :Dox<CR>
" goto definition with F8
"map <F6> :!cd ~/ngsf;make cli_pmake .<CR>
"map <F7> :!cd ~/ngsf;make xmsd_pmake .<CR>
"map <C-F6> :!cd ~/ngsf;make cli_ponly .<CR>
"map <C-F7> :!cd ~/ngsf;make xmsd_ponly .<CR>
"map <F8> <C-]>
"map <F9> <C-T>


"set foldmethod=indent
"set foldlevel=99
"map <leader>td <Plug>TaskList
"map <leader>g :GundoToggle<CR>
"let g:pyflakes_use_quickfix = 0
"filetype off
