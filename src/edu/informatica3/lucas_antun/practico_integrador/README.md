# Práctico Integrador - Sistema de Gestión de Turnos Médicos

**Alumno:** Lucas Antún  
**Materia:** Informática III  
**Universidad Nacional de Tucumán**

---

## 📁 Estructura del Proyecto

```
practico_integrador/
├── data/                           # Archivos CSV de datos
│   ├── pacientes_data.csv         # 30 pacientes
│   ├── medicos_data.csv           # 5 médicos
│   └── turnos_data.csv            # 121 turnos
├── docs/                           # Documentación técnica
│   └── VERIFICACION_COMPLETA.md   # Tests y verificaciones
├── consignas.txt                   # Consignas del profesor
└── *.java                          # 11 clases Java
```

---

## 🚀 Ejecución

Desde el directorio raíz del proyecto:

```bash
./ejecutar.sh
# Seleccionar opción 'e' (Práctico Integrador)
```

---

## 📋 Clases Implementadas

| Archivo | Líneas | Descripción |
|---------|--------|-------------|
| `Paciente.java` | 95 | Modelo de paciente con validaciones |
| `Medico.java` | 110 | Modelo de médico |
| `Turno.java` | 215 | Modelo de turno con detección de solapamiento |
| `AgendaMedico.java` | 580 | **AVL Tree** para agenda médica |
| `SalaEspera.java` | 210 | **Cola Circular** para sala de espera |
| `Recordatorio.java` | 85 | Modelo de recordatorio |
| `PlanificadorRecordatorios.java` | 340 | **Min-Heap** para recordatorios |
| `MapaPacientes.java` | 450 | **Hash Table** con chaining |
| `SolicitudCirugia.java` | 70 | Modelo de solicitud de cirugía |
| `PlanificadorQuirofano.java` | 255 | Planificador con heaps |
| `SistemaGestionTurnosMedicos.java` | 680 | **Sistema principal con menú** |

**Total:** 3,090 líneas de código documentado en español

---

## ✅ Ejercicios Implementados

| # | Ejercicio | Estado | Complejidad |
|---|-----------|--------|-------------|
| 1 | Carga CSV con validaciones | ✅ Completo | O(n) |
| 2 | Agenda médica (AVL Tree) | ✅ Completo | O(log n) |
| 3 | Buscar hueco libre | ✅ Completo | O(log n + k) |
| 4 | Sala de espera (Cola Circular) | ✅ Completo | O(1) |
| 5 | Recordatorios (Min-Heap) | ✅ Completo | O(log n) |
| 6 | Índice de pacientes (Hash Table) | ✅ Completo | O(1) promedio |
| 7 | Consolidador de agendas | ⚠️ Placeholder | Documentado |
| 8 | Reportes de ordenamiento | ⚠️ Placeholder | Documentado |
| 9 | Auditoría Undo/Redo | ⚠️ Placeholder | Documentado |
| 10 | Planificador de quirófano | ✅ Completo | O(S log Q + M log M) |

---

## 🎯 Características Principales

✅ **Estructuras de datos personalizadas** (implementadas desde cero)
- AVL Tree con rotaciones y balance automático
- Min-Heap con operación de reprogramación
- Hash Table con chaining y rehashing dinámico
- Cola Circular con gestión de desborde

✅ **Validaciones exhaustivas**
- DNI de 8 dígitos
- Nombres no vacíos
- Fechas válidas (rechaza pasado)
- Detección de solapamiento de turnos

✅ **Documentación completa en español**
- Todos los comentarios en español
- Javadoc en todas las clases
- Explicación de decisiones de diseño

✅ **Menu interactivo con 10 opciones**

---

## 📊 Datos de Prueba

- **30 pacientes** con DNI válidos
- **5 médicos** de diferentes especialidades
- **121 turnos válidos** (2 rechazados con fechas pasadas)
- Distribución balanceada entre médicos

---

## 📖 Documentación Adicional

Para más detalles técnicos, ver:
- `docs/VERIFICACION_COMPLETA.md` - Tests y verificación completa del sistema

---

**Última actualización:** 4 de Noviembre, 2024
