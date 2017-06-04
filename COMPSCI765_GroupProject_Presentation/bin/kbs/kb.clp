(deftemplate myTemp
	(slot one)
	(slot second))


(deffacts init
        (myTemp (one asd) (second jhg))
        (myTemp (one asd) (second kjh))
        (myTemp (one bvc) (second jhg))
        (myTemp (one bvc) (second jhg)))