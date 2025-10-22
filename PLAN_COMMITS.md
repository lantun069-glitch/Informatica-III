# 📋 Plan de Commits Colaborativos - Informática III

Este documento detalla la estrategia de commits para subir el proyecto de forma organizada entre Lucas y Federico.

## 🎯 Objetivo
Crear un historial de commits realista que muestre desarrollo colaborativo y progresivo del proyecto.

## 👥 División de Trabajo

### 🟦 LUCAS - Commits a realizar

#### 1️⃣ **Estructura Base del Proyecto**
```bash
# Agregar infraestructura básica
git add LICENSE ejecutar.sh
git commit -m "feat: Agregar licencia MIT y script de ejecución automatizado

- Implementar script ejecutar.sh con menú interactivo
- Añadir licencia MIT para el proyecto
- Configurar permisos y validaciones de Java"
```

#### 2️⃣ **Prácticos Básicos (1-3)**
```bash
# Agregar prácticos de fundamentos
git add src/edu/informatica3/lucas_antun/practico01/
git add src/edu/informatica3/lucas_antun/practico02/
git add src/edu/informatica3/lucas_antun/practico03/
git commit -m "feat: Implementar Prácticos 1-3 (POO, Recursividad, Algoritmos)

- Práctico 1: Sistema completo de gestión de tareas con persistencia
- Práctico 2: 8 ejercicios de recursividad con análisis de complejidad  
- Práctico 3: Sistema de pizzería con algoritmos de ordenamiento
- Incluir casos de prueba y documentación JavaDoc"
```

#### 3️⃣ **Práctico Integrador**
```bash
# Agregar proyecto integrador
git add src/edu/informatica3/lucas_antun/practico_integrador/
git commit -m "feat: Implementar Práctico Integrador - Sistema de Turnos Médicos

- Sistema completo de gestión de turnos médicos
- Carga de datos desde archivos CSV
- Validaciones robustas y reportes detallados
- Integra conceptos de POO, estructuras de datos y manejo de archivos"
```

---

### 🟩 FEDERICO - Commits a realizar

#### 4️⃣ **Estructuras de Datos Avanzadas (4-5)**
```bash
# Agregar estructuras de datos
git add src/edu/informatica3/lucas_antun/practico04/
git add src/edu/informatica3/lucas_antun/practico05/
git commit -m "feat: Implementar Prácticos 4-5 (Estructuras de Datos, Árboles AVL)

- Práctico 4: Implementación completa de Pilas genéricas con aplicaciones
- Práctico 5: Árboles AVL auto-balanceados con rotaciones y navegación
- Incluir demostraciones interactivas y análisis de rendimiento"
```

#### 5️⃣ **Árboles Rojo-Negro (6)**
```bash
# Agregar árboles avanzados
git add src/edu/informatica3/lucas_antun/practico06/
git commit -m "feat: Implementar Práctico 6 - Árboles Rojo-Negro

- Implementación completa de Red-Black Trees
- Verificación de las 5 propiedades fundamentales
- Sistema de colores type-safe y casos de rebalanceo
- 10 ejercicios demostrativos con test de estrés"
```

#### 6️⃣ **Archivos Compilados y Optimizaciones**
```bash
# Agregar archivos de compilación
git add bin/
git commit -m "build: Agregar archivos compilados y optimizaciones

- Incluir archivos .class compilados para ejecución directa
- Optimizar estructura de directorios para compilación
- Verificar compatibilidad con diferentes versiones de Java"
```

---

## 📋 Orden de Ejecución Recomendado

### Paso 1: Lucas ejecuta sus commits (1-3)
1. Commit de estructura base
2. Commit de prácticos 1-3
3. Commit del práctico integrador

### Paso 2: Federico ejecuta sus commits (4-6)
1. Pull de los cambios de Lucas: `git pull origin master`
2. Commit de prácticos 4-5
3. Commit de práctico 6
4. Commit de archivos compilados

## 🔄 Estrategia de Push (Subida al Repositorio)

### 🤔 ¿Cuándo hacer Push?

**Opción A: Push por separado (Recomendado para académico)**
```bash
# Lucas hace push de sus commits
git push origin master

# Federico después hace pull + sus commits + push
git pull origin master
# ... hace sus commits ...
git push origin master
```

**Opción B: Push conjunto al final**
```bash
# Lucas hace sus commits (quedan solo locales)
# Federico hace pull + sus commits (quedan solo locales)
# Al final Federico hace push de todo junto
git push origin master
```

### 📋 Comandos de Sincronización

#### Para Lucas (AHORA - después de tus commits):
```bash
git push origin master  # Subir tus 3 commits al repositorio
```

#### Para Federico (después):
```bash
git pull origin master  # Descargar commits de Lucas
# ... hacer sus 3 commits ...
git push origin master  # Subir todo junto
```

## ✅ Verificación Final

Al finalizar todos los commits, el historial debería mostrar:
- 7 commits en total (1 README + 6 features)
- Desarrollo progresivo y lógico
- División clara de responsabilidades
- Mensajes de commit profesionales

## 🚨 Notas Importantes

1. **Verificar estado antes de cada commit:**
   ```bash
   git status
   ```

2. **Si hay conflictos, resolverlos antes de continuar**

3. **Verificar que todos los archivos se agreguen correctamente:**
   ```bash
   git add -n [archivos]  # Ver qué se va a agregar sin hacerlo
   ```

4. **Probar compilación después de cada commit:**
   ```bash
   ./ejecutar.sh
   # Opción 'c' para compilar todo
   ```

## 📊 Resultado Esperado

Al finalizar, el repositorio tendrá:
- ✅ Estructura profesional completa
- ✅ Todos los prácticos implementados (1-6 + integrador)
- ✅ Documentación completa y actualizada
- ✅ Scripts de automatización
- ✅ Archivos compilados listos para usar
- ✅ Historial de commits realista y profesional

---

**👨‍💻 Desarrollado por: Lucas Santiago Said Antun & Federico Fernández**  
**📚 Materia: Informática III - Universidad Nacional de Córdoba**