(deftemplate disease "three symptom disease"
	(slot symptom1)
	(slot symptom2)
	(slot symptom3))
	
(deffacts initial-facts "initial facts"
	(disease(symptom1 cough)(symptom2 sneeze)(symptom3 fever))
	(disease(symptom1 cough)(symptom2 nose)(symptom3 fever)))
	
(defrule getdiag "flu"
	(disease(symptom1 cough)(symptom2 sneeze)(symptom3 fever))
=>
	(printout t "Flu" crlf))
