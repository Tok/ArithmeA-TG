(ns arithmea.gematria.qualifier
  (:require [arithmea.bot.emoji :refer :all]
            [arithmea.util :as util]))

(def reason-map
  {11   :master-number
   13   :fibonacci-number
   21   :fibonacci-number
   22   :master-number
   27   :cube-number
   33   :master-number
   34   :fibonacci-number
   44   :master-number
   55   :master-number
   56   :other
   61   :other
   64   :cube-number
   66   :master-number
   70   :weird-number
   72   :other
   77   :master-number
   88   :master-number
   89   :fibonacci-number
   93   :other
   99   :master-number
   100  :very-even
   111  :super-master
   121  :special-square
   125  :cube-number
   144  :fibonacci-number
   156  :other
   169  :special-square
   200  :very-even
   207  :other
   216  :cube-number
   222  :super-master
   225  :special-square
   256  :special-square
   233  :fibonacci-number
   300  :very-even
   322  :other
   333  :super-master
   341  :other
   343  :cube-number
   377  :fibonacci-number
   400  :very-even
   418  :other
   419  :other
   420  :other
   444  :super-master
   500  :very-even
   504  :other
   506  :other
   512  :cube-number
   529  :special-square
   555  :super-master
   600  :very-even
   610  :fibonacci-number
   616  :other
   666  :super-master
   696  :other
   700  :very-even
   729  :cube-number
   777  :super-master
   800  :very-even
   836  :weird-number
   888  :super-master
   900  :very-even
   987  :fibonacci-number
   999  :super-master
   1000 :cube-number
   1089 :special-square
   1111 :hyper-master
   1156 :special-square
   1331 :cube-number
   1337 :other
   1597 :fibonacci-number
   1728 :cube-number
   1776 :other
   2000 :very-even
   2197 :cube-number
   2222 :hyper-master
   2584 :fibonacci-number
   2744 :cube-number
   3000 :very-even
   3333 :hyper-master
   3375 :cube-number
   4000 :very-even
   4030 :weird-number
   4096 :cube-number
   4181 :fibonacci-number
   4444 :hyper-master
   4913 :cube-number
   5000 :very-even
   5555 :hyper-master
   5830 :weird-number
   5832 :cube-number
   6000 :very-even
   6666 :hyper-master
   6765 :fibonacci-number
   6859 :cube-number
   7000 :very-even
   7192 :weird-number
   7777 :hyper-master
   7912 :weird-number
   8000 :cube-number
   8888 :hyper-master
   9000 :very-even
   9001 :other
   9261 :cube-number
   9272 :weird-number
   9999 :hyper-master
   })

(def ^:private qualifier-map
  {:fibonacci-number (green-asterisk)
   :cube-number      (orange-asterisk)
   :master-number    (exclamation-mark)
   :super-master     (double-exclamation-mark)
   :hyper-master     (double-exclamation-mark)
   :very-even        (hundred)
   :other            (brightness)})

(defn- secondary-qualifier [n]
  (if (util/prime? n) (blue-p) (if (even? n) (white-square) (white-circle))))

(defn highlight-symbol [n]
  (let [reason (get reason-map (int n))
        pq (get qualifier-map reason)
        sq (secondary-qualifier n)]
    (if (nil? pq) sq pq)))
