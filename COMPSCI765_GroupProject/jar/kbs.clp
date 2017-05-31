(defrule influenza 
(disease-is influenza)
=>
(printout t dacryoadenitis" "headache" "myalgia" "nausea and vomiting" "fever" "suppurative otitis media" "dysgeusia crlf))

(defrule pneumonia 
(disease-is pneumonia)
=>
(printout t abdominal pain" "productive cough" "bronchial breathing" "shortness of breath" "bronchospasm" "chest expansion poor" "chest pain" "chest wall percussion dull" "cough" "hemoptysis" "fever" "vocal fremitus increased crlf))

(defrule upper respiratory tract infection 
(disease-is upper respiratory tract infection)
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

(defrule allergic contact dermatitis 
(disease-is allergic contact dermatitis)
=>
(printout t rash crlf))

(defrule altitude sickness 
(disease-is altitude sickness)
=>
(printout t delirium" "ataxia" "shortness of breath" "cheyne stokes respirations" "dizzy" "headache" "nausea and vomiting" "edema" "coma crlf))

(defrule anorexia nervosa 
(disease-is anorexia nervosa)
=>
(printout t amenorrhoea" "constipation" "delayed puberty" "impotence" "female infertility" "lanugo hair" "muscle wasting" "purpura" "weight loss crlf))

(defrule cerebrovascular accident 
(disease-is cerebrovascular accident)
=>
(printout t delirium" "cerebellar ataxia" "dementia" "cranial nerve palsy" "dizzy" "dysarthria" "dysphagia" "aphasia" "hoarseness" "headache" "hypertension" "urinary incontinence" "dystonia" "muscle weakness" "nystagmus" "numbness" "spasticity" "tremor" "upper motor neurone lesion" "blindness crlf))

(defrule peptic ulceration 
(disease-is peptic ulceration)
=>
(printout t abdominal pain" "gastrointestinal bleeding crlf))

(defrule gastroenteritis 
(disease-is gastroenteritis)
=>
(printout t abdominal pain" "nausea and vomiting crlf))

(defrule gastroesophageal reflux 
(disease-is gastroesophageal reflux)
=>
(printout t abdominal pain" "chest pain" "cough" "dysphagia crlf))

(defrule allergic rhinitis 
(disease-is allergic rhinitis)
=>
(printout t conjunctival edema" "epiphora" "rhinitis crlf))

(defrule rheumatoid arthritis 
(disease-is rheumatoid arthritis)
=>
(printout t arthritis" "bursitis" "carpal tunnel syndrome" "hoarseness" "episcleritis" "genu valgum" "lymphadenopathy" "lymphedema" "mononeuritis multiplex" "palmar erythema" "fever" "raynaud phenomenon" "stridor" "subcutaneous nodules" "tendon nodules" "uveitis crlf))

(defrule diabetes mellitus type 1 
(disease-is diabetes mellitus type 1)
=>
(printout t cataracts" "charcot joints" "mononeuritis multiplex" "peripheral neuropathy" "failure to thrive" "adie pupil crlf))

(defrule diabetes mellitus type 2 
(disease-is diabetes mellitus type 2)
=>
(printout t cranial nerve palsy" "abdominal pain" "amenorrhoea" "blepharoptosis" "cataracts" "diplopia" "impotence" "female infertility" "hypertension" "mononeuritis multiplex" "osmotic diuresis" "proximal muscle weakness" "proximal myopathy" "sensory neuropathy" "adie pupil" "weight gain" "weight loss crlf))

(defrule myocardial infarction 
(disease-is myocardial infarction)
=>
(printout t shortness of breath" "cardiac arrest" "chest pain" "hyperhidrosis" "hypotension" "nausea and vomiting crlf))

(defrule angina pectoris 
(disease-is angina pectoris)
=>
(printout t abdominal pain" "cardiac arrest" "chest pain" "hypotension crlf))

(defrule heat stroke 
(disease-is heat stroke)
=>
(printout t delirium" "headache" "purpura" "coma crlf))

(defrule dacryoadenitis 
(symptom-is dacryoadenitis)
=>
(printout t influenza" "tuberculosis" "measles" " crlf))

(defrule headache 
(symptom-is headache)
=>
(printout t influenza" "upper respiratory tract infection" "altitude sickness" "cerebrovascular accident" "heat stroke" " crlf))

(defrule myalgia 
(symptom-is myalgia)
=>
(printout t influenza" " crlf))

(defrule nausea and vomiting 
(symptom-is nausea and vomiting)
=>
(printout t influenza" "altitude sickness" "gastroenteritis" "myocardial infarction" " crlf))

(defrule fever 
(symptom-is fever)
=>
(printout t influenza" "pneumonia" "tuberculosis" "measles" "aids" "rheumatoid arthritis" " crlf))

(defrule suppurative otitis media 
(symptom-is suppurative otitis media)
=>
(printout t influenza" "measles" " crlf))

(defrule dysgeusia 
(symptom-is dysgeusia)
=>
(printout t influenza" " crlf))

(defrule abdominal pain 
(symptom-is abdominal pain)
=>
(printout t pneumonia" "peptic ulceration" "gastroenteritis" "gastroesophageal reflux" "diabetes mellitus type 2" "angina pectoris" " crlf))

(defrule productive cough 
(symptom-is productive cough)
=>
(printout t pneumonia" "copd" "tuberculosis" "measles" " crlf))

(defrule bronchial breathing 
(symptom-is bronchial breathing)
=>
(printout t pneumonia" " crlf))

(defrule shortness of breath 
(symptom-is shortness of breath)
=>
(printout t pneumonia" "copd" "tuberculosis" "altitude sickness" "myocardial infarction" " crlf))

(defrule bronchospasm 
(symptom-is bronchospasm)
=>
(printout t pneumonia" "asthma" " crlf))

(defrule chest expansion poor 
(symptom-is chest expansion poor)
=>
(printout t pneumonia" "asthma" " crlf))

(defrule chest pain 
(symptom-is chest pain)
=>
(printout t pneumonia" "gastroesophageal reflux" "myocardial infarction" "angina pectoris" " crlf))

(defrule chest wall percussion dull 
(symptom-is chest wall percussion dull)
=>
(printout t pneumonia" " crlf))

(defrule cough 
(symptom-is cough)
=>
(printout t pneumonia" "upper respiratory tract infection" "copd" "tuberculosis" "asthma" "measles" "gastroesophageal reflux" " crlf))

(defrule hemoptysis 
(symptom-is hemoptysis)
=>
(printout t pneumonia" "tuberculosis" " crlf))

(defrule vocal fremitus increased 
(symptom-is vocal fremitus increased)
=>
(printout t pneumonia" " crlf))

(defrule cervical lymphadenopathy 
(symptom-is cervical lymphadenopathy)
=>
(printout t upper respiratory tract infection" "tuberculosis" " crlf))

(defrule earache 
(symptom-is earache)
=>
(printout t upper respiratory tract infection" " crlf))

(defrule deafness 
(symptom-is deafness)
=>
(printout t upper respiratory tract infection" " crlf))

(defrule rhinitis 
(symptom-is rhinitis)
=>
(printout t upper respiratory tract infection" "allergic rhinitis" " crlf))

(defrule sore throat 
(symptom-is sore throat)
=>
(printout t upper respiratory tract infection" " crlf))

(defrule amenorrhoea 
(symptom-is amenorrhoea)
=>
(printout t tuberculosis" "anorexia nervosa" "diabetes mellitus type 2" " crlf))

(defrule cognitive impairment 
(symptom-is cognitive impairment)
=>
(printout t tuberculosis" " crlf))

(defrule cachexia 
(symptom-is cachexia)
=>
(printout t tuberculosis" "aids" " crlf))

(defrule carpal tunnel syndrome 
(symptom-is carpal tunnel syndrome)
=>
(printout t tuberculosis" "rheumatoid arthritis" " crlf))

(defrule cranial nerve palsy 
(symptom-is cranial nerve palsy)
=>
(printout t tuberculosis" "cerebrovascular accident" "diabetes mellitus type 2" " crlf))

(defrule dactylitis 
(symptom-is dactylitis)
=>
(printout t tuberculosis" " crlf))

(defrule digital clubbing 
(symptom-is digital clubbing)
=>
(printout t tuberculosis" " crlf))

(defrule erythema nodosum 
(symptom-is erythema nodosum)
=>
(printout t tuberculosis" " crlf))

(defrule female infertility 
(symptom-is female infertility)
=>
(printout t tuberculosis" "anorexia nervosa" "diabetes mellitus type 2" " crlf))

(defrule goiter 
(symptom-is goiter)
=>
(printout t tuberculosis" " crlf))

(defrule inguinal swelling 
(symptom-is inguinal swelling)
=>
(printout t tuberculosis" " crlf))

(defrule hepatomegaly 
(symptom-is hepatomegaly)
=>
(printout t tuberculosis" " crlf))

(defrule kyphosis 
(symptom-is kyphosis)
=>
(printout t tuberculosis" " crlf))

(defrule laryngeal stenosis 
(symptom-is laryngeal stenosis)
=>
(printout t tuberculosis" " crlf))

(defrule butterfly rash 
(symptom-is butterfly rash)
=>
(printout t tuberculosis" " crlf))

(defrule failure to thrive 
(symptom-is failure to thrive)
=>
(printout t tuberculosis" "asthma" "diabetes mellitus type 1" " crlf))

(defrule uveitis 
(symptom-is uveitis)
=>
(printout t tuberculosis" "rheumatoid arthritis" " crlf))

(defrule cardiac arrest 
(symptom-is cardiac arrest)
=>
(printout t asthma" "myocardial infarction" "angina pectoris" " crlf))

(defrule hyperinflated chest 
(symptom-is hyperinflated chest)
=>
(printout t asthma" " crlf))

(defrule pectus carinatum 
(symptom-is pectus carinatum)
=>
(printout t asthma" " crlf))

(defrule pulsus paradoxus 
(symptom-is pulsus paradoxus)
=>
(printout t asthma" " crlf))

(defrule conjunctivitis 
(symptom-is conjunctivitis)
=>
(printout t measles" " crlf))

(defrule lymphadenopathy 
(symptom-is lymphadenopathy)
=>
(printout t upper respiratory tract infection" "tuberculosis" "measles" "aids" "rheumatoid arthritis" " crlf))

(defrule maculopapular rash 
(symptom-is maculopapular rash)
=>
(printout t measles" "aids" " crlf))

(defrule dementia 
(symptom-is dementia)
=>
(printout t aids" "cerebrovascular accident" " crlf))

(defrule cotton wool spots 
(symptom-is cotton wool spots)
=>
(printout t aids" " crlf))

(defrule diarrhea 
(symptom-is diarrhea)
=>
(printout t aids" " crlf))

(defrule mononeuritis multiplex 
(symptom-is mononeuritis multiplex)
=>
(printout t aids" "rheumatoid arthritis" "diabetes mellitus type 1" "diabetes mellitus type 2" " crlf))

(defrule parotid gland enlargement 
(symptom-is parotid gland enlargement)
=>
(printout t aids" " crlf))

(defrule retinopathy 
(symptom-is retinopathy)
=>
(printout t aids" " crlf))

(defrule rash 
(symptom-is rash)
=>
(printout t tuberculosis" "measles" "aids" "allergic contact dermatitis" " crlf))

(defrule delirium 
(symptom-is delirium)
=>
(printout t altitude sickness" "cerebrovascular accident" "heat stroke" " crlf))

(defrule ataxia 
(symptom-is ataxia)
=>
(printout t altitude sickness" "cerebrovascular accident" " crlf))

(defrule cheyne stokes respirations 
(symptom-is cheyne stokes respirations)
=>
(printout t altitude sickness" " crlf))

(defrule dizzy 
(symptom-is dizzy)
=>
(printout t altitude sickness" "cerebrovascular accident" " crlf))

(defrule edema 
(symptom-is edema)
=>
(printout t altitude sickness" "allergic rhinitis" "rheumatoid arthritis" " crlf))

(defrule coma 
(symptom-is coma)
=>
(printout t altitude sickness" "heat stroke" " crlf))

(defrule constipation 
(symptom-is constipation)
=>
(printout t anorexia nervosa" " crlf))

(defrule delayed puberty 
(symptom-is delayed puberty)
=>
(printout t anorexia nervosa" " crlf))

(defrule impotence 
(symptom-is impotence)
=>
(printout t anorexia nervosa" "diabetes mellitus type 2" " crlf))

(defrule lanugo hair 
(symptom-is lanugo hair)
=>
(printout t anorexia nervosa" " crlf))

(defrule muscle wasting 
(symptom-is muscle wasting)
=>
(printout t anorexia nervosa" " crlf))

(defrule purpura 
(symptom-is purpura)
=>
(printout t anorexia nervosa" "heat stroke" " crlf))

(defrule weight loss 
(symptom-is weight loss)
=>
(printout t anorexia nervosa" "diabetes mellitus type 2" " crlf))

(defrule cerebellar ataxia 
(symptom-is cerebellar ataxia)
=>
(printout t cerebrovascular accident" " crlf))

(defrule dysarthria 
(symptom-is dysarthria)
=>
(printout t cerebrovascular accident" " crlf))

(defrule dysphagia 
(symptom-is dysphagia)
=>
(printout t cerebrovascular accident" "gastroesophageal reflux" " crlf))

(defrule aphasia 
(symptom-is aphasia)
=>
(printout t cerebrovascular accident" " crlf))

(defrule hoarseness 
(symptom-is hoarseness)
=>
(printout t cerebrovascular accident" "rheumatoid arthritis" " crlf))

(defrule hypertension 
(symptom-is hypertension)
=>
(printout t cerebrovascular accident" "diabetes mellitus type 2" " crlf))

(defrule urinary incontinence 
(symptom-is urinary incontinence)
=>
(printout t cerebrovascular accident" " crlf))

(defrule dystonia 
(symptom-is dystonia)
=>
(printout t cerebrovascular accident" " crlf))

(defrule muscle weakness 
(symptom-is muscle weakness)
=>
(printout t cerebrovascular accident" "diabetes mellitus type 2" " crlf))

(defrule nystagmus 
(symptom-is nystagmus)
=>
(printout t cerebrovascular accident" " crlf))

(defrule numbness 
(symptom-is numbness)
=>
(printout t cerebrovascular accident" " crlf))

(defrule spasticity 
(symptom-is spasticity)
=>
(printout t cerebrovascular accident" " crlf))

(defrule tremor 
(symptom-is tremor)
=>
(printout t cerebrovascular accident" " crlf))

(defrule upper motor neurone lesion 
(symptom-is upper motor neurone lesion)
=>
(printout t cerebrovascular accident" " crlf))

(defrule blindness 
(symptom-is blindness)
=>
(printout t cerebrovascular accident" " crlf))

(defrule gastrointestinal bleeding 
(symptom-is gastrointestinal bleeding)
=>
(printout t peptic ulceration" " crlf))

(defrule conjunctival edema 
(symptom-is conjunctival edema)
=>
(printout t allergic rhinitis" " crlf))

(defrule epiphora 
(symptom-is epiphora)
=>
(printout t allergic rhinitis" " crlf))

(defrule arthritis 
(symptom-is arthritis)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule bursitis 
(symptom-is bursitis)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule episcleritis 
(symptom-is episcleritis)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule genu valgum 
(symptom-is genu valgum)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule lymphedema 
(symptom-is lymphedema)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule palmar erythema 
(symptom-is palmar erythema)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule raynaud phenomenon 
(symptom-is raynaud phenomenon)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule stridor 
(symptom-is stridor)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule subcutaneous nodules 
(symptom-is subcutaneous nodules)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule tendon nodules 
(symptom-is tendon nodules)
=>
(printout t rheumatoid arthritis" " crlf))

(defrule cataracts 
(symptom-is cataracts)
=>
(printout t diabetes mellitus type 1" "diabetes mellitus type 2" " crlf))

(defrule charcot joints 
(symptom-is charcot joints)
=>
(printout t diabetes mellitus type 1" " crlf))

(defrule peripheral neuropathy 
(symptom-is peripheral neuropathy)
=>
(printout t diabetes mellitus type 1" " crlf))

(defrule adie pupil 
(symptom-is adie pupil)
=>
(printout t diabetes mellitus type 1" "diabetes mellitus type 2" " crlf))

(defrule blepharoptosis 
(symptom-is blepharoptosis)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule diplopia 
(symptom-is diplopia)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule osmotic diuresis 
(symptom-is osmotic diuresis)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule proximal muscle weakness 
(symptom-is proximal muscle weakness)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule proximal myopathy 
(symptom-is proximal myopathy)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule sensory neuropathy 
(symptom-is sensory neuropathy)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule weight gain 
(symptom-is weight gain)
=>
(printout t diabetes mellitus type 2" " crlf))

(defrule hyperhidrosis 
(symptom-is hyperhidrosis)
=>
(printout t myocardial infarction" " crlf))

(defrule hypotension 
(symptom-is hypotension)
=>
(printout t myocardial infarction" "angina pectoris" " crlf))

