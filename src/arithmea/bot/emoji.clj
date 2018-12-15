(ns arithmea.bot.emoji)

(defn- keycap [key]
  ; See https://unicode.org/emoji/charts/full-emoji-list.html#keycap
  (let [i (- (int key) 48)
        number (if (and (>= i 0) (<= i 9)) (+ i 48) 48)
        modifier 65039                                      ;FEOF
        secondary 8419]                                     ;20E3
    (String. (int-array [number modifier secondary]) 0 3)))

(defn numerical-char? [c] (let [i (int c)] (and (>= i 48) (<= i 57))))

(defn emojify [string]
  (apply str (map #(if (numerical-char? %) (keycap %) %) string)))

(defn blue-p [] (String. (int-array [127359 65039]) 0 2))

(defn- code->emoji [codepoint] (String. (Character/toChars codepoint)))
(defn green-asterisk [] (code->emoji 0x2733))
(defn orange-asterisk [] (code->emoji 0x2734))
(defn exclamation-mark [] (code->emoji 0x2757))
(defn double-exclamation-mark [] (code->emoji 0x203C))
(defn hundred [] (code->emoji 0x1F4AF))
(defn brightness [] (code->emoji 0x1F506))
(defn white-square [] (code->emoji 0x2B1C))
(defn black-square [] (code->emoji 0x2B1B))
(defn white-circle [] (code->emoji 0x26AA))
(defn black-circle [] (code->emoji 0x26AB))
