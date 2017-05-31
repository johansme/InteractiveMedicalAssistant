(defrule influenza 
(disease-is influenza)
=>
(printout t dacryoadenitis" "headache" "myalgia" "nausea and vomiting" "fever" "suppurative otitis media" "dysgeusia crlf))

(defrule pneumonia 
(disease-is pneumonia)
=>
(printout t abdominal pain" "productive cough" "bronchial breathing" "shortness of breath" "bronchospasm" "chest expansion poor" "chest pain" "chest wall percussion dull" "cough" "hemoptysis" "fever" "vocal fremitus increased crlf))

(defrule upper-respiratory-tract-infection 
(disease-is upper-respiratory-tract-infection)
=>
(printout t cervical lymphadenopathy" "cough" "earache" "headache" "deafness" "rhinitis" "sore throat crlf))

(defrule copd 
(disease-is copd)
=>
(printout t productive cough" "shortness of breath" "cough crlf))

(defrule tuberculosis 
(disease-is tuberculosis)
=>
(printout t productive cough" "amenorrhoea" "cognitive impairment" "shortness of breath" "cachexia" "carpal tunnel syndrome" "cervical lymphadenopathy" "cough" "cranial nerve palsy" "dacryoadenitis" "dactylitis" "digital clubbing" "erythema nodosum" "female infertility" "goiter" "inguinal swelling" "hemoptysis" "hepatomegaly" "kyphosis" "laryngeal stenosis" "butterfly rash" "fever" "failure to thrive" "uveitis crlf))

(defrule asthma 
(disease-is asthma)
=>
(printout t bronchospasm" "cardiac arrest" "chest expansion poor" "hyperinflated chest" "cough" "pectus carinatum" "pulsus paradoxus" "failure to thrive crlf))

(defrule measles 
(disease-is measles)
=>
(printout t productive cough" "conjunctivitis" "dacryoadenitis" "lymphadenopathy" "maculopapular rash" "fever" "suppurative otitis media crlf))

(defrule aids 
(disease-is aids)
=>
(printout t cachexia" "dementia" "cotton wool spots" "diarrhea" "lymphadenopathy" "maculopapular rash" "mononeuritis multiplex" "parotid gland enlargement" "fever" "retinopathy crlf))

(defrule allergic-contact-dermatitis 
(disease-is allergic-contact-dermatitis)
=>
(printout t rash crlf))

(defrule altitude-sickness 
(disease-is altitude-sickness)
=>
(printout t delirium" "ataxia" "shortness of breath" "cheyne stokes respirations" "dizzy" "headache" "nausea and vomiting" "edema" "coma crlf))

(defrule anorexia-nervosa 
(disease-is anorexia-nervosa)
=>
(printout t amenorrhoea" "constipation" "delayed puberty" "impotence" "female infertility" "lanugo hair" "muscle wasting" "purpura" "weight loss crlf))

(defrule cerebrovascular-accident 
(disease-is cerebrovascular-accident)
=>
(printout t delirium" "cerebellar ataxia" "dementia" "cranial nerve palsy" "dizzy" "dysarthria" "dysphagia" "aphasia" "hoarseness" "headache" "hypertension" "urinary incontinence" "dystonia" "muscle weakness" "nystagmus" "numbness" "spasticity" "tremor" "upper motor neurone lesion" "blindness crlf))

(defrule peptic-ulceration 
(disease-is peptic-ulceration)
=>
(printout t abdominal pain" "gastrointestinal bleeding crlf))

(defrule gastroenteritis 
(disease-is gastroenteritis)
=>
(printout t abdominal pain" "nausea and vomiting crlf))

(defrule gastroesophageal-reflux 
(disease-is gastroesophageal-reflux)
=>
(printout t abdominal pain" "chest pain" "cough" "dysphagia crlf))

(defrule allergic-rhinitis 
(disease-is allergic-rhinitis)
=>
(printout t conjunctival edema" "epiphora" "rhinitis crlf))

(defrule rheumatoid-arthritis 
(disease-is rheumatoid-arthritis)
=>
(printout t arthritis" "bursitis" "carpal tunnel syndrome" "hoarseness" "episcleritis" "genu valgum" "lymphadenopathy" "lymphedema" "mononeuritis multiplex" "palmar erythema" "fever" "raynaud phenomenon" "stridor" "subcutaneous nodules" "tendon nodules" "uveitis crlf))

(defrule diabetes-mellitus-type-1 
(disease-is diabetes-mellitus-type-1)
=>
(printout t cataracts" "charcot joints" "mononeuritis multiplex" "peripheral neuropathy" "failure to thrive" "adie pupil crlf))

(defrule diabetes-mellitus-type-2 
(disease-is diabetes-mellitus-type-2)
=>
(printout t cranial nerve palsy" "abdominal pain" "amenorrhoea" "blepharoptosis" "cataracts" "diplopia" "impotence" "female infertility" "hypertension" "mononeuritis multiplex" "osmotic diuresis" "proximal muscle weakness" "proximal myopathy" "sensory neuropathy" "adie pupil" "weight gain" "weight loss crlf))

(defrule myocardial-infarction 
(disease-is myocardial-infarction)
=>
(printout t shortness of breath" "cardiac arrest" "chest pain" "hyperhidrosis" "hypotension" "nausea and vomiting crlf))

(defrule angina-pectoris 
(disease-is angina-pectoris)
=>
(printout t abdominal pain" "cardiac arrest" "chest pain" "hypotension crlf))

(defrule heat-stroke 
(disease-is heat-stroke)
=>
(printout t delirium" "headache" "purpura" "coma crlf))