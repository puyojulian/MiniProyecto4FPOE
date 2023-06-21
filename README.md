# MiniProyecto4FPOE - SISTEMA DE GESTION DE PAGOS
Fundamentos de programacion orientado a objetos, miniproyecto IV

Autores:
- Juan David Rodriguez Rubio.
- Manuel Felipe Cardoso Forero.
- Sebastian Orrego.
- Julian Puyo.
- Luis Carlos Lucero.

## Necesidades o requerimientos de usario
La Cooperativa de corteros de caña Serviagricola “Los Suareños” S.A.S ha contactado la 
EISC de Univalle para que un grupo de desarrollo de software diseñe y construya una aplicación 
que les permita gestionar el proceso de liquidación de nómina del corte de caña.

## Requerimientos 
- La aplicación deberá permitir gestionar (CRUD) las siguientes entidades involucradas en el 
proceso: Empleado, EPS, Fondo de Pensión, ARL, Caja de Compensación, Configuración de 
la Empresa, Conceptos de Devengo, Conceptos de Deducciones.
- La aplicación deberá permitir realizar el proceso de liquidación de la nómina que se efectúa 
cada 15 días, registrado por empleado un conjunto de devengos y un conjunto de deducciones.
- En la liquidación de la nómina hay conceptos de deducción que son automáticos, como el 
pago de salud y Fondo de pensión, el cuál es el 4% para cada uno sobre la sumatoria de todos los devengos que hacen base. 
Otras deducciones se generan por valor, como por ejemplo préstamo a empleado. Cuando la sumatoria de devengos que hacen base es inferior al salario 
mínimo vigente, esa deducción se realiza con base en el SMLV
- La aplicación deberá realizar el proceso de liquidación de prestaciones sociales (Cesantías, 
Interés de Cesantías, Primas y Vacaciones) las cuales se realizan semestralmente o cuando 
em empleada se retira de la cooperativa.
- Las prestaciones sociales se calculan sobre el total acumulado de los devengos que hacen 
base, así: 8.33% Cesantías, 1.0% interés de Cesantías, 8.33% Primas, 4.17% Vacaciones.
- El concepto de devengo por corte de caña dependerá del tipo de caña (Cruda o Quemada) y 
del día (Ordinario o Festivo), cada tipo de caña tiene una tarifa por tonelada que rige durante 
el año. Para saber cada empleado cuanto cortó, el ingenio envía un archivo plano con la información del corte de una semana (ver estructura del archivo al final del documento)
- La aplicación deberá almacenar información de forma persistente, utilizando archivos de texto 
o binarios.
- La aplicación deberá tener una interfaz gráfica de usuario que permita gestionar todas sus funciones.
- La información deberá ser persistente en archivo de texto o binario, como lo desee.
- El software deberá permitir exportar los datos básicos de los empleados y deberá quedar 
como archivo de texto delimitado por punto y coma.
- El software deberá permitir efectuar copias de respaldo (backup) y restauración de la información de las nóminas generadas, en un archivo de texto o binario, como lo desee.
