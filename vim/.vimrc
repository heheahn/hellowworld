set nocompatible

" set the runtime path to include Vundle and initialize
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
" let Vundle manage Vundle, required
Plugin 'gmarik/Vundle.vim'
Plugin 'tmhedberg/SimpylFold'
Plugin 'vim-scripts/indentpython.vim'
Bundle 'Valloric/YouCompleteMe'
"Plugin 'scrooloose/syntastic'
Plugin 'nvie/vim-flake8'
Plugin 'scrooloose/nerdtree'
Plugin 'jistr/vim-nerdtree-tabs'
Plugin 'kien/ctrlp.vim'
Plugin 'Lokaltog/powerline', {'rtp': 'powerline/bindings/vim/'}
Plugin 'posva/vim-vue'
Plugin 'SirVer/ultisnips'
Plugin 'honza/vim-snippets'
Plugin 'VisIncr'
Plugin 'othree/html5.vim'
Plugin 'pangloss/vim-javascript'
Plugin 'wesQ3/vim-windowswap'

" All of your Plugins must be added before the following line
call vundle#end()

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
autocmd filetype python setlocal sw=4 sts=4 et
autocmd filetype javascript setlocal sw=2 sts=2 et
"autocmd FileType vue syntax sync fromstart
autocmd BufRead,BufNewFile *.vue set filetype=html.javascript
"autocmd BufRead,BufNewFile *.vue setlocal filetype=vue.javascript.html.css
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
"colorscheme xoria256
colorscheme apprentice
set autochdir


set encoding=utf-8
set scrolloff=3
set showmode
set showcmd
set wildmenu
set wildmode=list:longest
set ttyfast
set ruler
set laststatus=2
set clipboard=unnamed
set complete=.,w,b,u,t,i

set timeoutlen=1000
set ttimeoutlen=0


" Enable folding
set foldmethod=indent
set foldlevel=99
" Enable folding with the spacebar
nnoremap <space> za

map <silent> <leader>c : !cd ~/nexgfw/trunk/components/apps/web2py;make test;cd ~/nexgfw/trunk/work/rfs/usr/local;cp ~/nexgfw/trunk/components/libs/libdncrypto/dn_crypto.py ~/nexgfw/trunk/work/rfs/usr/local/web2py/applications/nexgfw/modules/;tar cvf a.tar web2py;mv a.tar ~/share<CR>

"if &cp | set nocp | endif
"set nocp
"let s:cpo_save=&cpo
"let OmniCpp_NamespaceSearch = 2
set tags=./.TAGS,./tags,~/tags,~/xios/linux/embedded_rootfs/tags

" recreate tags file with 8F
map <C-F8> :!ctags -R --c++-kinds=+pl --fields=+iaS --extra=+q .<CR>
"map <C-F12> :!make;make install .<CR>
"map <C-F11> :!cd ~/turtle/trunk/src/webadmin/resources;make install .<CR>
" create doxygen comment
map <F6> :Dox<CR>
map <F8> <C-]>
map <F9> <C-T>

let python_highlight_all=1
let NERDTreeIgnore=['\.pyc$', '\~$'] "ignore files in NERDTree


let g:DoxygenToolkit_authorName="Gerhard Gappmeier <gerhard.gappmeier@ascolab.com>"

let g:ycm_autoclose_preview_window_after_completion=1
let g:ycm_collect_identifiers_from_tags_files = 1
map <leader>g  :YcmCompleter GoToDefinitionElseDeclaration<CR>

let g:UltiSnipsExpandTrigger = "<C-j>"
" make YCM compatible with UltiSnips (using supertab)
let g:ycm_key_list_select_completion = ['<C-n>', '<Down>']
let g:ycm_key_list_previous_completion = ['<C-p>', '<Up>']
let g:SuperTabDefaultCompletionType = '<C-n>'

" better key bindings for UltiSnipsExpandTrigger
let g:UltiSnipsExpandTrigger = "<tab>"
let g:UltiSnipsJumpForwardTrigger = "<tab>"
let g:UltiSnipsJumpBackwardTrigger = "<s-tab>"

"let g:flake8_show_in_gutter=1
"let g:flake8_show_quickfix=0
let g:flake8_show_in_file=1   " show
autocmd FileType python map <buffer> <F7> :call Flake8()<CR>
" autocmd BufWritePost *.py call Flake8()

let g:ctrlp_root_markers = ['.ctrlp']
let g:ctrlp_custom_ignore = {
  \ 'dir':  '\v[\/]\.?(git|hg|svn|node_modules|gens)$',
  \ 'file': '\v\.(exe|so|c|cc|h|o)$',
  \ }
