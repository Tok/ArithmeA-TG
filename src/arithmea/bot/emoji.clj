(ns arithmea.bot.emoji)

(defn- keycap [key]
  ;See https://unicode.org/emoji/charts/full-emoji-list.html#keycap
  (let [i (- (int key) 48)
        number (if (and (>= i 0) (<= i 9)) (+ i 48) 48)
        modifier 65039                                      ;FEOF
        secondary 8419]                                     ;20E3
    (String. (int-array [number modifier secondary]) 0 3)
    ))

(defn numerical-char? [c] (let [i (int c)] (and (>= i 48) (<= i 57))))

(defn emojify [string]
  (apply str (map #(if (numerical-char? %) (keycap %) %) string))
  )
