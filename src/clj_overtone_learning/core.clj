(ns clj-overtone-learning.core
  (:use [overtone.live]
        [overtone.synth.stringed]
        [overtone.inst.drum]
        [overtone.inst.piano]))

(def g (guitar))

;; bow down to the power chord!
(ctl g :pre-amp 4.0 :distort 0.99)
(guitar-strum g [0 2 2 -1 -1 -1])
(guitar-strum g [3 5 5 -1 -1 -1])




(defn play-chord [a-chord]
  (doseq [note a-chord] (piano note)))

(play-chord (chord :D3 :major))

(def three-twenty-bpm (metronome 320))

(defn looper [nome sound]
  (let [beat (nome)]
    (at (nome beat) (sound))
    (apply-at (nome (inc beat)) looper nome sound [])))

(looper three-twenty-bpm kick)

(kick4)

;; play a chord progression on our piano
(let [time (now)]
  (at time (play-chord (chord :D3 :major7)))
  (at (+ 2000 time) (play-chord (chord :A3 :major)))
  (at (+ 3000 time) (play-chord (chord :A3 :major7)))
  (at (+ 4300 time) (play-chord (chord :F3 :major7))))

(defn loop-beats [time]
  (at (+ 0 time) (kick))
  (at (+ 400 time) (hat3))
  (at (+ 800 time) (kick))
  (at (+ 1200 time) (hat3))
  (apply-at (+ 1600 time) loop-beats (+ 1600 time) []))

(loop-beats (now))

(stop)
