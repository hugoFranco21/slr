# SLR
Proyecto Final para la materia de Compiladores

## Correr el proyecto
- Para correr el proyecto primero hay que clonarlo a la máquina local, para esto se utiliza el sig. comando:
> git clone https://github.com/hugoFranco21/slr.git

- Posteriormente entras al proyecto con:
> cd slr

- Y adentro solo se necesita usar el script de run.sh. Se le pueden otorgar permisos de ejecución o llamarlo con algún shell. FILE_NAME se debe reemplazar por el nombre del archivo con la gramática, existe un ejemplo en la carpeta input llamado file1.txt

Escenario 1:
> chmod u+x run.sh  

> ./run.sh FILE_NAME

Escenario 2:
> sh run.sh FILE_NAME

## Recomendaciones generales
- Correr en WSL 
- Agregar una linea en el script de run.sh que copie la salida de la carpeta output/ al ambiente windows para poder 
verlo en el navegador.
> cp -r output/ /mnt/c/users/{$USER}/desktop

### Problemas conocidos
- Si bien la gramática de input/file1.txt la resuelve sin problema, la de input/file2.txt tiene un error al generar la table de SLRClosure, la gramática incluyendo first y follows está bien, pero no genera bien la tabla
- En general, gramáticas que tengan left-recursion no las puede resolver correctamente.
- Debería poder generar las tablas para cualquier gramática LL.