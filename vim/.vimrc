if &cp | set nocp | endif
set nocp
filetype plugin indent on
set autoindent
let s:cpo_save=&cpo
"let OmniCpp_NamespaceSearch = 2
"set tags=./.TAGS,./.tags,~/tags/cpptags
map! <S-Insert> <MiddleMouse>
nmap gx <Plug>NetrwBrowseX
nnoremap <silent> <Plug>NetrwBrowseX :call netrw#NetBrowseX(expand("<cWORD>"),0)
nnoremap <silent> <F5> :let _s=@/<Bar>:%s/\s\+$//e<Bar>:let @/=_s<Bar>:nohl<CR>
map <S-Insert> <MiddleMouse>
let &cpo=s:cpo_save
unlet s:cpo_save
runtime! ftplugin/man.vim
set autochdir
set background=dark
set backspace=indent,eol,start
set expandtab
set mouse=a
set fileencodings=ucs-bom,utf-8,default,euc-kr,latin1
set nobomb
set guifont=Terminus\ 10
"set guifont=Terminus\ Bitstream\ Vera\ Sans\ Mono\ 9
set helplang=ko
set history=50
set nomodeline
"set mouse=a
set printoptions=paper:a4
set ruler
"set runtimepath=~/.vim,/usr/share/vim/vimfiles,/usr/share/vim/vim72,/var/lib/vim/addons/after,~/.vim/after,~/turtle/trunk/include/turtle,~/turtle/trunk/src/pm,~/turtle/trunk/src/webadmin
set path=.,~/.vim/tags/cpp_src
set shiftwidth=2
set suffixes=.bak,~,.swp,.o,.info,.aux,.log,.dvi,.bbl,.blg,.brf,.cb,.ind,.idx,.ilg,.inx,.out,.toc
set termencoding=utf-8
set viminfo='20,\"50
set incsearch
set nu
set tabstop=4
set softtabstop=2
set smarttab
set expandtab
"set smartcase
set shiftround
set def=^\\s*#\\s*define
set inc=^\\s*#\\s*include
set tag=./tags,~/.vim/tags/cpp,~/.vim/tags/gl,~/ngsf/COMPONENTS/management/tags,~/ngsf/COMPONENTS/misc/libxml2/tags,~/turtle/trunk/libsrc/tags
set tags+=~/.vim/tags/cpp
set tags+=~/.vim/tags/gl
set cscopetag
set csprg=cscope
set scr=10
set nowrap
set cursorline
set nf=octal,hex
set cinoptions=>s:0=s(0W4g0hsi2st0
set cindent
set ignorecase
set fen
set foldlevel=2
set dir=~/.vim.tmp
set backupdir=~/.vim.backup
set hls
set list listchars=tab:»›,trail:·
set makeprg=(cd\ `git\ rev-parse\ --show-toplevel`;make\ xmsd_pmake)
"autocmd FileType c,idl,cpp,java,php,xml autocmd BufWritePre <buffer> :call setline(1,map(getline(1,"$"),'substitute(v:val,"\\s\\+$","","")'))
"set color=murphy
syntax on
" vim: set ft=vim :
autocmd BufWritePre * :%s/\s\+$//e
func! Sts()
  let st = expand("<cword>")
  exe "sts ".st
endfunc
nmap ,st : call Sts() <cr>

 " disable vi compatibility (emulation of old bugs)
set nocompatible
" use indentation of previous line
"##set autoindent
 " use intelligent indentation for C
"set smartindent
" wrap lines at 120 chars. 80 is somewaht antiquated with nowadays displays.
set textwidth=120
" turn syntax highlighting on
set t_Co=256
"colorscheme wormbat
colorscheme xoria256
" turn line numbers on
set number
" highlight matching braces
set showmatch
" intelligent comments
set comments=sl:/*,mb:\ *,elx:\ */
let g:DoxygenToolkit_authorName="Gerhard Gappmeier <gerhard.gappmeier@ascolab.com>"

" recreate tags file with 8F
map <C-F8> :!ctags -R --c++-kinds=+pl --fields=+iaS --extra=+q .<CR>
"map <C-F12> :!make;make install .<CR>
"map <C-F11> :!cd ~/turtle/trunk/src/webadmin/resources;make install .<CR>
" create doxygen comment
"map <F7> :Dox<CR>
" goto definition with F8
map <F6> :!cd ~/ngsf;make cli_pmake .<CR>
map <F7> :!cd ~/ngsf;make xmsd_pmake .<CR>
map <C-F6> :!cd ~/ngsf;make cli_ponly .<CR>
map <C-F7> :!cd ~/ngsf;make xmsd_ponly .<CR>
map <F8> <C-]>
map <F9> <C-T>
"map ,diff   :VCSVimDiff<CR>
"map ,up     :VCSUpdate<CR>
"map ,ci     :VCSCommit<CR>
"map ,add    :VCSAdd<CR>
"map ,del    :VCSDelete<CR>
"map ,revert :VCSRevert<CR>

" OmniCppComplete
"let OmniCpp_NamespaceSearch = 1
"let OmniCpp_GlobalScopeSearch = 1
"let OmniCpp_ShowAccess = 1
"let OmniCpp_ShowPrototypeInAbbr = 1 " show function parameters
"let OmniCpp_MayCompleteDot = 1 " autocomplete after .
"let OmniCpp_MayCompleteArrow = 1 " autocomplete after ->
"let OmniCpp_MayCompleteScope = 1 " autocomplete after ::
"let OmniCpp_DefaultNamespaces = ["std", "_GLIBCXX_STD"]
" automatically open and close the popup menu / preview window
"au CursorMovedI,InsertLeave * if pumvisible() == 0|silent! pclose|endif
"set completeopt=menuone,menu,longest,preview

" Doxygen
"let g:DoxygenToolkit_blockHeader="--------------------------------------------------------------------------"
"let g:DoxygenToolkit_blockFooter="----------------------------------------------------------------------------"

set foldmethod=indent
set foldlevel=99
let mapleader = ","
map <leader>td <Plug>TaskList
map <leader>g :GundoToggle<CR>
let g:pyflakes_use_quickfix = 0
"filetype off
"call pathogen#runtime_append_all_bundles()
"call pathogen#helptags()
filetype plugin on
let g:pydiction_location='~/.vim/after/ftplugin/complete-dict'
