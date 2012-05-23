;; ----------------------------------------------------------------------------
;; My own configuration ------------------------------------------------- BEGIN
;; ----------------------------------------------------------------------------
(add-hook 'find-file-hook (lambda ()(linum-mode 1)))
;; 하단에 커서가 위치한 라인 넘버 표시
(line-number-mode 1)
;; 하단에 커서가 위치한 컬럼 넘버 표시
(column-number-mode 1)

;;(setq-default indent-tabs-mode nil)
;;(setq c-default-style "linux")
;;(setq c-default-style "stroustrup")

(define-key global-map (kbd "RET") 'newline-and-indent)
;; ===== Set the highlight current line minor mode =====

;; In every buffer, the line which contains the cursor will be fully
;; highlighted

(global-hl-line-mode 1)

  ;; ===== Set standard indent to 2 rather that 4 ====
(setq standard-indent 2)

  ;; ===== Turn off tab character =====

;;
;; Emacs normally uses both tabs and spaces to indent lines. If you
;; prefer, all indentation can be made from spaces only. To request this,
;; set `indent-tabs-mode' to `nil'. This is a per-buffer variable;
;; altering the variable affects only the current buffer, but it can be
;; disabled for all buffers.

;;
;; Use (setq ...) to set value locally to a buffer
;; Use (setq-default ...) to set value globally
;;
(setq-default indent-tabs-mode nil)

  ;; ========== Set the fill column ==========

;; Enable backup files.
(setq-default fill-column 72)

  ;; ===== Function to delete a line =====

;; First define a variable which will store the previous column position
(defvar previous-column nil "Save the column position")

;; Define the nuke-line function. The line is killed, then the newline
;; character is deleted. The column which the cursor was positioned at is then
;; restored. Because the kill-line function is used, the contents deleted can
;; be later restored by usibackward-delete-char-untabifyng the yank commands.
(defun nuke-line()
  "Kill an entire line, including the trailing newline character"
  (interactive)

  ;; Store the current column position, so it can later be restored for a more
  ;; natural feel to the deletion
  (setq previous-column (current-column))

  ;; Now move to the end of the current line
  (end-of-line)

  ;; Test the length of the line. If it is 0, there is no need for a
  ;; kill-line. All that happens in this case is that the new-line character
  ;; is deleted.
  (if (= (current-column) 0)
    (delete-char 1)

    ;; This is the 'else' clause. The current line being deleted is not zero
    ;; in length. First remove the line by moving to its start and then
    ;; killing, followed by deletion of the newline character, and then
    ;; finally restoration of the column position.
    (progn
      (beginning-of-line)
      (kill-line)
      (delete-char 1)
      (move-to-column previous-column))))

;; Now bind the delete line function to the F8 key
(global-set-key [f8] 'nuke-line)

;; ----------------------------------------------------------------------------
;; Path
;; ----------------------------------------------------------------------------
;(setq load-path (cons (expand-file-name "~") load-path))
;(setq load-path (cons (expand-file-name "~/.emacs.d") load-path))
;(setq load-path (cons (expand-file-name "~/.emacs.d/personal") load-path))

(add-to-list 'load-path "~")
(add-to-list 'load-path "~/.emacs.d")
(add-to-list 'load-path "~/.emacs.d/personal")
(add-to-list 'load-path "~/.emacs.d/vendor")

;; ----------------------------------------------------------------------------
;; Enable menu-bar and tool-bar
;; ----------------------------------------------------------------------------
(menu-bar-mode 1)
(tool-bar-mode 1)

;; ----------------------------------------------------------------------------
;; Backup File
;; ----------------------------------------------------------------------------
(setq make-backup-files nil)

;; -----------------------------------------------------------------------------
;; Global key binding
;; -----------------------------------------------------------------------------
(global-set-key "\C-l" 'goto-line)      ; [Ctrl]-[L]


;; M-x 키 바인딩
(global-set-key "\C-x\C-m" 'execute-extended-command)
(global-set-key "\C-c\C-m" 'execute-extended-command)
;; Backword 키 바인딩
(global-set-key "\C-w" 'backward-kill-word)
(global-set-key "\C-x\C-k" 'kill-region)
(global-set-key "\C-c\C-k" 'kill-region)
;; -----------------------------------------------------------------------------
;; Visible BELL
;; -----------------------------------------------------------------------------
(setq visible-bell t)

;; ----------------------------------------------------------------------------
;; Easier Transition between Windows
;; ----------------------------------------------------------------------------
;  M-up, M-down, M-left, and M-right keys.
(windmove-default-keybindings 'meta)

;; ----------------------------------------------------------------------------
;; Hangul configuration for Windows
;; ----------------------------------------------------------------------------
(if (eq window-system 'w32)
    (progn
      (set-fontset-font "fontset-default" 'korean-ksc5601 "-outline-나눔고딕-normal-normal-normal-mono-*-*-*-*-p-*-iso8859-1")
      (setq initial-frame-alist '((top . 10) (left . 100)))
      (setq default-frame-alist
        (append
         '(
           (font . "-*-Monaco-normal-r-*-*-12-102-120-120-c-*-iso8859-1")
           (width . 140)
           (height . 47)
           )
         default-frame-alist))))

; Consolas Font
;           (font . "-*-Consolas-normal-r-*-*-12-102-120-120-c-*-iso8859-1")
; Monaco Font which should be installed.
;           (font . "-*-Monaco-normal-r-*-*-12-102-120-120-c-*-iso8859-1")

;; ----------------------------------------------------------------------------
;; Hangul configuration for Linux
;; ----------------------------------------------------------------------------
(if (eq system-type 'gnu/linux)
    (progn
      (set-fontset-font "fontset-default" 'korean-ksc5601 "-unknown-나눔고딕-normal-normal-normal-*-*-*-*-*-*-0-iso10646-1")
      (setq initial-frame-alist '((top . 10) (left . 100)))
      (setq default-frame-alist
        (append
         '(
           (font . "-*-Monaco-normal-normal-normal-*-13-*-*-*-m-0-iso10646-1")
           (width . 140)
           (height . 80)
           )
         default-frame-alist))))

;; -----------------------------------------------------------------------------
;; Copy Word & Line & Paragraph
;; -----------------------------------------------------------------------------
(defun copy-line (&optional arg)
  "Save current line into Kill-Ring without mark the line "
  (interactive "P")
  (let ((beg (line-beginning-position))
        (end (line-end-position)))
    (copy-region-as-kill beg end))
  )
(global-set-key (kbd "C-c l")         (quote copy-line))

(defun copy-paragraph (&optional arg)
  "Copy paragraphes at point"
  (interactive "P")
  (let ((beg (progn (backward-paragraph 1) (point)))
        (end (progn (forward-paragraph arg) (point))))
    (copy-region-as-kill beg end))
  )
(global-set-key (kbd "C-c p")         (quote copy-paragraph))

(defun copy-line (&optional arg)
  "Do a kill-line but copy rather than kill.  This function directly calls
kill-line, so see documentation of kill-line for how to use it including prefix
argument and relevant variables.  This function works by temporarily making the
buffer read-only, so I suggest setting kill-read-only-ok to t."
  (interactive "P")
  (toggle-read-only 1)
  (kill-line arg)
  (toggle-read-only 0))

(setq-default kill-read-only-ok t)
(global-set-key "\C-c\C-k" 'copy-line)

;; -----------------------------------------------------------------------------
;; Open new line
;; -----------------------------------------------------------------------------
(local-set-key [(control o)] 'vi-open-next-line)
(defun vi-open-next-line (arg)
  "Move to the next line (like vi) and then opens a line."
  (interactive "p")
  (end-of-line)
  (open-line arg)
  (next-line 1)
  (indent-according-to-mode))

;; -----------------------------------------------------------------------------
;; Moving around
;; -----------------------------------------------------------------------------
(local-set-key [(control s)] 'isearch-forward-regexp)
(local-set-key [(control r)] 'isearch-backward-regexp)
;; Always end searches at the beginning of the matching expression.
(add-hook 'isearch-mode-end-hook 'custom-goto-match-beginning)
(defun custom-goto-match-beginning ()
  "Use with isearch hook to end search at first char of match."
  (when isearch-forward (goto-char isearch-other-end)))

;; ----------------------------------------------------------------------------
;; Add occur to isearch
;; ----------------------------------------------------------------------------
(define-key isearch-mode-map (kbd "C-o")
  (lambda ()
    (interactive)
    (let ((case-fold-search isearch-case-fold-search))
      (occur (if isearch-regexp isearch-string
               (regexp-quote isearch-string))))))

;; ----------------------------------------------------------------------------
;; Balancing your parentheses
;; ----------------------------------------------------------------------------
(setq show-paren-delay 0)           ; how long to wait?
(show-paren-mode t)                 ; turn paren-mode on
(setq show-paren-style 'expression) ; alternatives are 'parenthesis' and 'mixed'

(set-face-background 'show-paren-match-face "RoyalBlue4")
(set-face-attribute 'show-paren-match-face nil
        :weight 'bold :underline nil :overline nil :slant 'normal)

(set-face-foreground 'show-paren-mismatch-face "red")
(set-face-attribute 'show-paren-mismatch-face nil
                    :weight 'bold :underline t :overline nil :slant 'normal)


;; ----------------------------------------------------------------------------
;; From here, additional EL file should be installed.
;; ----------------------------------------------------------------------------

;; ----------------------------------------------------------------------------
;; flymake
;; http://flymake.sourceforge.net/
;; ----------------------------------------------------------------------------
(when (load "flymake" t)
         (defun flymake-pyflakes-init ()
           (let* ((temp-file (flymake-init-create-temp-buffer-copy
                              'flymake-create-temp-inplace))
              (local-file (file-relative-name
                           temp-file
                           (file-name-directory buffer-file-name))))
             (list "pyflakes" (list local-file))))

         (add-to-list 'flymake-allowed-file-name-masks
                  '("\\.py\\'" flymake-pyflakes-init)))

(add-hook 'find-file-hook 'flymake-find-file-hook)


;; ----------------------------------------------------------------------------
;; smex
;; https://github.com/nonsequitur/smex
;; ----------------------------------------------------------------------------
(require 'smex)
(smex-initialize)

(global-set-key (kbd "M-x") 'smex)
(global-set-key (kbd "M-X") 'smex-major-mode-commands)
;; This is your old M-x.
(global-set-key (kbd "C-c C-c M-x") 'execute-extended-command)



;; ----------------------------------------------------------------------------
;; color-theme
;; ----------------------------------------------------------------------------

(setq load-path (cons (expand-file-name "~/.emacs.d/") load-path))
(require 'color-theme)
    (color-theme-initialize)
;;    (color-theme-jsc-dark)


;; ----------------------------------------------------------------------------
;; Solazied-color theme
;; ----------------------------------------------------------------------------
(setq load-path (cons (expand-file-name "~/.emacs.d/personal/emacs-color-theme-solarized") load-path))
(require 'color-theme-solarized)
(color-theme-solarized-dark)
;; ----------------------------------------------------------------------------
;; snippets
;; ----------------------------------------------------------------------------

(add-to-list 'load-path
              "~/.emacs.d/plugins/yasnippet")
(require 'yasnippet) ;; not yasnippet-bundle
(yas/initialize)
(yas/load-directory "~/.emacs.d/plugins/yasnippet/snippets")

;; ----------------------------------------------------------------------------
;; cedet
;; ----------------------------------------------------------------------------

(global-ede-mode 1)
(require 'semantic/sb)
(semantic-mode 1)

(setq semanticdb-default-save-directory "~/.emacs.d/#semanticdb.cache#")
(semantic-add-system-include "/usr/include/c++/4.4.5/" 'c++-mode)

(global-set-key [(control return)] 'semantic-ia-complete-symbol-menu)
(global-set-key [(control shift return)] 'semantic-ia-complete-tip)
(global-set-key [(control c)(control return)] 'semantic-complete-analyze-inline)
(global-set-key [(shift return)] 'semantic-analyze-possible-completions)

;; ----------------------------------------------------------------------------
(provide 'tkhwang-dotemacs) ; --------------------------------------------- END
;; ----------------------------------------------------------------------------
