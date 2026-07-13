# Langues — Especificación de Producto, UX y Sistema Visual

> Documento funcional y visual para una aplicación móvil de aprendizaje activo de idiomas construida con Kotlin, Jetpack Compose y Room.

---

## 1. Propósito del documento

Este documento define la experiencia completa de la aplicación **Langues**:

- La visión y personalidad del producto.
- Los valores que debe comunicar la interfaz.
- La arquitectura de navegación.
- Las pantallas principales y secundarias.
- Los estados vacíos, de carga, error y confirmación.
- El sistema visual.
- Los tokens de diseño.
- Las paletas para `light mode` y `dark mode`.
- Las reglas de tipografía, espaciado, elevación, iconografía y movimiento.
- Los principios de accesibilidad.
- Los criterios que deben guiar la implementación en Jetpack Compose.

La aplicación se concibe como un **notebook de aprendizaje activo**, no como una simple aplicación de flashcards. El objetivo es permitir que el usuario capture conocimiento lingüístico, lo organice, lo convierta en ejercicios y lo repase mediante repetición espaciada.

---

# 2. Visión del producto

## 2.1. Idea central

Langues debe funcionar como una combinación de:

- Cuaderno personal.
- Base de conocimiento lingüístico.
- Sistema de repetición espaciada.
- Herramienta de práctica activa.
- Registro de progreso.
- Biblioteca personal de idiomas.

El usuario no debe sentir que está rellenando una base de datos. Debe sentir que está construyendo, poco a poco, su propio mapa de conocimiento del idioma.

El flujo conceptual es:

```text
Capturar
   ↓
Organizar
   ↓
Comprender
   ↓
Practicar
   ↓
Revisar
   ↓
Consolidar
```

## 2.2. Promesa de producto

> Todo lo que aprendes puede convertirse en algo que puedes practicar.

La aplicación debe reducir la distancia entre:

- Encontrar una palabra nueva.
- Guardarla.
- Entenderla.
- Recordarla.
- Poder utilizarla activamente.

---

# 3. Personalidad de la aplicación

## 3.1. Sentimiento general

La interfaz debe transmitir:

- Calma.
- Concentración.
- Curiosidad.
- Progreso continuo.
- Claridad.
- Control personal.
- Sensación de crecimiento.
- Motivación sin presión.

No debe parecer:

- Infantil.
- Excesivamente gamificada.
- Académica o fría.
- Saturada de estadísticas.
- Competitiva.
- Agresiva con las rachas.
- Una aplicación corporativa.
- Una copia visual de un gestor de tareas.

## 3.2. Valores del producto

### Aprendizaje activo

El usuario no solo almacena información. La utiliza, la produce, la corrige y la recuerda.

### Propiedad del conocimiento

Los datos pertenecen al usuario. Deben poder exportarse, importarse y conservarse sin depender de una cuenta o servidor.

### Flexibilidad

Cada idioma puede organizarse de manera distinta. La aplicación ofrece una estructura inicial, pero no obliga a mantenerla.

### Progreso honesto

El dominio no se representa mediante recompensas artificiales. Se basa en el rendimiento, la frecuencia de recuerdo y la dificultad real.

### Serenidad

La aplicación anima a estudiar, pero no castiga. Un día sin estudiar no debe producir mensajes negativos ni sensación de fracaso.

### Profundidad progresiva

La primera interacción debe ser sencilla. Las opciones avanzadas deben aparecer cuando aporten valor, no desde el primer momento.

### Privacidad

La primera versión debe funcionar completamente offline. La aplicación no necesita cuenta para ser útil.

---

# 4. Principios de experiencia de usuario

## 4.1. El siguiente paso debe ser evidente

En cada pantalla debe existir una acción principal clara:

- Crear un idioma.
- Añadir un concepto.
- Empezar un repaso.
- Guardar.
- Importar.
- Continuar una sesión.

## 4.2. Crear contenido debe ser rápido

Añadir una palabra básica debe requerir pocos campos:

```text
Palabra
Significado
Guardar
```

Los demás campos deben ser opcionales y desplegables.

## 4.3. La complejidad se revela progresivamente

Los campos avanzados, relaciones, adjuntos, plantillas y opciones del algoritmo deben estar disponibles, pero no visibles de forma permanente.

## 4.4. La información importante debe ser escaneable

Las pantallas deben usar:

- Jerarquía tipográfica clara.
- Espacios generosos.
- Pocos colores simultáneos.
- Bloques de contenido reconocibles.
- Etiquetas breves.
- Iconos consistentes.

## 4.5. El color no debe ser el único indicador

Los estados deben comunicarse mediante:

- Texto.
- Icono.
- Forma.
- Color.
- Etiqueta de estado.

## 4.6. La práctica debe tener poco ruido visual

Durante una sesión de estudio deben desaparecer los elementos no esenciales. El contenido y la respuesta del usuario deben ser el centro.

---

# 5. Arquitectura de información

## 5.1. Navegación principal

La navegación inferior tendrá cuatro destinos:

1. **Hoy**
2. **Idiomas**
3. **Biblioteca**
4. **Progreso**

Un quinto destino, **Ajustes**, se abre desde el avatar o menú superior y no ocupa espacio permanente en la barra inferior.

```text
Hoy | Idiomas | Biblioteca | Progreso
```

## 5.2. Acción flotante contextual

El `FloatingActionButton` cambia según la pantalla:

| Pantalla | Acción |
|---|---|
| Hoy | Repaso rápido |
| Idiomas | Añadir idioma |
| Detalle de idioma | Añadir concepto |
| Biblioteca | Añadir concepto |
| Importaciones | Importar archivo |
| Plantillas | Crear plantilla |

## 5.3. Jerarquía de navegación

```text
App
├── Onboarding
├── Hoy
│   ├── Configurar sesión
│   ├── Sesión de estudio
│   └── Resumen de sesión
├── Idiomas
│   ├── Crear idioma
│   ├── Detalle de idioma
│   │   ├── Sección
│   │   ├── Concepto
│   │   ├── Crear/editar concepto
│   │   ├── Configurar secciones
│   │   └── Ajustes del idioma
├── Biblioteca
│   ├── Búsqueda
│   ├── Filtros
│   ├── Concepto
│   └── Colecciones
├── Progreso
│   ├── Resumen
│   ├── Estadísticas por idioma
│   ├── Retención
│   └── Historial
├── Importar y exportar
│   ├── Seleccionar archivo
│   ├── Vista previa
│   ├── Conflictos
│   └── Resultado
└── Ajustes
    ├── Apariencia
    ├── Estudio
    ├── Datos y copias
    ├── Accesibilidad
    └── Acerca de
```

---

# 6. Inventario completo de pantallas

## 6.1. Lanzamiento y onboarding

### S01 — Splash screen

**Objetivo:** mostrar una transición breve mientras se inicializan Room, preferencias y estado de sesión.

**Contenido:**

- Logotipo.
- Nombre de la aplicación.
- Fondo del color `surface`.
- Indicador de progreso solo si la carga supera unos cientos de milisegundos.

**Reglas:**

- No mostrar textos promocionales.
- No utilizar animaciones largas.
- Si existe un error de inicialización, navegar a una pantalla de recuperación.

---

### S02 — Bienvenida

**Objetivo:** explicar el valor principal sin sobrecargar.

**Mensaje principal:**

> Convierte tus apuntes en conocimiento que puedes recordar y utilizar.

**Contenido:**

- Ilustración abstracta relacionada con notas, tarjetas y conexiones.
- Tres beneficios breves:
  - Organiza cada idioma a tu manera.
  - Practica con repetición espaciada.
  - Conserva y exporta tus datos.
- Botón principal: `Empezar`.
- Acción secundaria: `Importar una biblioteca`.

**No debe incluir:**

- Registro obligatorio.
- Permisos innecesarios.
- Carrusel de cinco o más pantallas.

---

### S03 — Crear primer idioma

**Objetivo:** permitir que el usuario llegue a contenido útil rápidamente.

**Campos:**

- Nombre del idioma.
- Código opcional.
- Idioma base o de traducción.
- Color identificativo.
- Icono o abreviatura.
- Nivel inicial opcional.
- Plantilla de secciones.

**Plantillas disponibles:**

- General.
- Conversación.
- Académica.
- Personalizada.

**Acción principal:** `Crear idioma`.

---

### S04 — Preferencias iniciales

**Objetivo:** configurar únicamente lo necesario para comenzar.

**Opciones:**

- Objetivo diario aproximado.
- Duración preferida de sesión.
- Recordatorios activados o desactivados.
- Tema del sistema, claro u oscuro.
- Iniciar con conceptos nuevos o solo cuando el usuario los añada.

**Regla:** todos los valores deben poder cambiarse más tarde.

---

## 6.2. Área Hoy

### S10 — Inicio / Hoy

**Objetivo:** responder a la pregunta: “¿Qué debería practicar ahora?”

**Jerarquía:**

1. Saludo contextual breve.
2. Resumen de pendientes.
3. Acción principal de estudio.
4. Idiomas activos.
5. Conceptos débiles.
6. Actividad reciente.

**Bloques:**

#### Tarjeta principal de repaso

- Número de tarjetas pendientes.
- Tiempo estimado.
- Distribución entre nuevos, aprendizaje y repaso.
- Botón `Empezar repaso`.

#### Objetivo diario

- Progreso del día.
- Tiempo estudiado.
- Conceptos revisados.
- No utilizar mensajes de culpa.

#### Idiomas activos

Cada idioma muestra:

- Nombre.
- Tarjetas pendientes.
- Retención aproximada.
- Última actividad.

#### Conceptos que requieren atención

- Errores recurrentes.
- Tarjetas suspendidas recientemente.
- Contenido con dominio bajo.

#### Captura rápida

Acciones:

- Nueva palabra.
- Nueva expresión.
- Nueva regla gramatical.
- Nuevo error.
- Nota libre.

**Estado vacío:**

> Añade tu primer concepto para empezar a construir tu notebook.

---

### S11 — Selector de sesión

**Objetivo:** configurar una sesión de repaso.

**Opciones:**

- Idioma.
- Duración:
  - 5 minutos.
  - 10 minutos.
  - 20 minutos.
  - Sin límite.
- Cantidad máxima de tarjetas.
- Incluir conceptos nuevos.
- Incluir tarjetas atrasadas.
- Tipos de ejercicio.
- Orden:
  - Inteligente.
  - Solo débiles.
  - Aleatorio.
  - Por sección.

**Resumen previo:**

```text
Francés
15 tarjetas
≈ 8 minutos
Reconocimiento, producción y cloze
```

**Acción principal:** `Comenzar`.

---

### S12 — Sesión de estudio

**Objetivo:** permitir practicar con el mínimo de distracciones.

**Elementos permanentes:**

- Barra de progreso.
- Nombre o abreviatura del idioma.
- Acción para salir.
- Acción para editar o suspender la tarjeta.

**Zona central:**

- Tipo de ejercicio.
- Pregunta o estímulo.
- Campo de respuesta.
- Audio, imagen o contexto cuando corresponda.
- Botón `Comprobar`.

**Después de responder:**

- Respuesta correcta.
- Respuesta del usuario.
- Explicación.
- Ejemplo.
- Valoración:
  - Otra vez.
  - Difícil.
  - Bien.
  - Fácil.

**Tipos de pantalla dentro de la sesión:**

- Reconocimiento.
- Producción escrita.
- Cloze.
- Ordenar oración.
- Opción múltiple.
- Dictado.
- Corrección de error.
- Producción libre.
- Pronunciación.

**Reglas visuales:**

- Una sola tarea visible.
- Máximo contraste.
- Botones de respuesta grandes.
- No mostrar estadísticas extensas durante la sesión.
- Evitar celebraciones intrusivas.

---

### S13 — Pausa de sesión

**Objetivo:** permitir interrumpir sin perder progreso.

**Acciones:**

- Continuar.
- Terminar y guardar.
- Reiniciar tarjeta actual.
- Salir sin finalizar.

**Información:**

- Tarjetas completadas.
- Tiempo.
- Progreso guardado.

---

### S14 — Resumen de sesión

**Objetivo:** cerrar el ciclo con información útil.

**Contenido:**

- Tarjetas revisadas.
- Respuestas correctas.
- Tarjetas que vuelven pronto.
- Conceptos fortalecidos.
- Conceptos con dificultad.
- Tiempo total.
- Comparación con la sesión anterior, solo si resulta positiva y clara.

**Acciones:**

- Revisar errores.
- Continuar con otra sesión.
- Volver a Hoy.

**Mensaje de tono:**

> Buen trabajo. Has reforzado 12 conceptos y detectado 3 que necesitan otra vuelta.

---

## 6.3. Área Idiomas

### S20 — Lista de idiomas

**Objetivo:** mostrar todos los idiomas como espacios de conocimiento independientes.

**Cada tarjeta de idioma incluye:**

- Nombre.
- Código.
- Color.
- Nivel opcional.
- Número de conceptos.
- Pendientes de hoy.
- Retención.
- Última actividad.
- Menú contextual.

**Acciones contextuales:**

- Abrir.
- Editar.
- Exportar.
- Duplicar estructura.
- Archivar.
- Eliminar.

**Filtros:**

- Activos.
- Archivados.
- Todos.

**Estado vacío:**

> Crea un idioma y empieza a guardar lo que aprendes.

---

### S21 — Crear idioma

**Objetivo:** definir un nuevo espacio lingüístico.

**Campos básicos:**

- Nombre.
- Idioma base.
- Código.
- Color.
- Icono.
- Nivel.

**Secciones generadas por defecto:**

- Vocabulario.
- Gramática.
- Expresiones.
- Pronunciación.
- Errores frecuentes.
- Notas.

**Opciones:**

- Activar o desactivar secciones.
- Crear plantilla personalizada.
- Importar contenido inicial.
- Copiar estructura de otro idioma.

---

### S22 — Detalle del idioma

**Objetivo:** funcionar como dashboard del idioma.

**Cabecera:**

- Nombre.
- Nivel.
- Color.
- Menú.
- Progreso.
- Pendientes.

**Bloques:**

- Continuar aprendiendo.
- Secciones.
- Conceptos recientes.
- Conceptos débiles.
- Etiquetas frecuentes.
- Actividad.
- Acceso a estadísticas del idioma.

**Acción principal:** `Añadir concepto`.

---

### S23 — Lista de secciones

**Objetivo:** visualizar y gestionar la estructura del idioma.

**Cada sección muestra:**

- Nombre.
- Icono.
- Número de conceptos.
- Pendientes.
- Dominio promedio.
- Última modificación.

**Acciones:**

- Abrir.
- Editar.
- Reordenar.
- Ocultar.
- Duplicar.
- Eliminar.

---

### S24 — Configurar secciones

**Objetivo:** permitir personalizar el notebook.

**Funciones:**

- Arrastrar para reordenar.
- Renombrar.
- Cambiar icono.
- Cambiar color secundario.
- Crear subsección.
- Marcar como solo notas.
- Marcar como generadora de ejercicios.
- Ocultar sin eliminar.
- Restaurar secciones predeterminadas.

---

### S25 — Detalle de sección

**Objetivo:** mostrar los conceptos pertenecientes a una categoría.

**Vista disponible:**

- Lista.
- Tarjetas compactas.
- Agrupación por etiquetas.
- Agrupación por nivel.

**Filtros:**

- Estado de aprendizaje.
- Nivel.
- Etiqueta.
- Tipo de concepto.
- Pendiente.
- Con errores.
- Sin ejemplos.
- Sin tarjetas.

**Acciones:**

- Añadir concepto.
- Selección múltiple.
- Exportar sección.
- Iniciar repaso de la sección.

---

## 6.4. Conceptos y contenido

### S30 — Captura rápida

**Objetivo:** guardar algo antes de olvidarlo.

**Campos visibles:**

- Idioma.
- Tipo.
- Título o palabra.
- Significado o nota breve.

**Opciones desplegables:**

- Sección.
- Etiquetas.
- Ejemplo.
- Nivel.
- Crear tarjetas automáticamente.

**Acciones:**

- Guardar.
- Guardar y añadir otro.
- Abrir editor completo.

---

### S31 — Selector de tipo de concepto

**Tipos disponibles:**

- Vocabulario.
- Expresión.
- Gramática.
- Pronunciación.
- Error.
- Nota.
- Texto.
- Referencia.

Cada tipo muestra una descripción de una línea.

---

### S32 — Crear o editar vocabulario

**Campos principales:**

- Palabra o expresión.
- Traducción.
- Definición en idioma objetivo.
- Categoría gramatical.
- Género.
- Singular y plural.
- Pronunciación.
- Nivel.
- Registro.
- Región.

**Bloques adicionales:**

- Ejemplos.
- Sinónimos.
- Antónimos.
- Palabras relacionadas.
- Audio.
- Imagen.
- Fuente.
- Etiquetas.
- Notas.
- Tarjetas generadas.

**Acciones:**

- Guardar.
- Guardar y practicar.
- Duplicar.
- Archivar.
- Eliminar.

---

### S33 — Crear o editar expresión

**Campos:**

- Expresión.
- Significado real.
- Traducción literal.
- Contexto.
- Registro.
- Región.
- Variantes.
- Ejemplos.
- Errores habituales.
- Etiquetas.
- Fuente.
- Audio.

---

### S34 — Crear o editar gramática

**Campos:**

- Título.
- Resumen.
- Explicación.
- Fórmula o estructura.
- Ejemplos correctos.
- Ejemplos incorrectos.
- Excepciones.
- Casos de uso.
- Nivel.
- Conceptos relacionados.
- Ejercicios.
- Fuente.

---

### S35 — Crear o editar pronunciación

**Campos:**

- Sonido.
- IPA.
- Descripción.
- Posición articulatoria.
- Audio.
- Palabras de ejemplo.
- Pares mínimos.
- Errores frecuentes.
- Región o acento.

---

### S36 — Crear o editar error

**Campos:**

- Producción incorrecta.
- Corrección.
- Explicación.
- Contexto.
- Fecha.
- Fuente del error:
  - Conversación.
  - Ejercicio.
  - Profesor.
  - Texto.
  - Otro.
- Número de veces cometido.
- Conceptos relacionados.
- Crear ejercicio de corrección.

---

### S37 — Crear o editar nota libre

**Objetivo:** permitir contenido no estructurado.

**Contenido:**

- Título.
- Texto enriquecido básico.
- Listas.
- Enlaces.
- Archivos adjuntos.
- Etiquetas.
- Relación con conceptos.

---

### S38 — Detalle de concepto

**Objetivo:** mostrar toda la información de un concepto sin entrar en edición.

**Cabecera:**

- Tipo.
- Título.
- Significado.
- Estado.
- Dominio.
- Próximo repaso.

**Secciones:**

- Información principal.
- Ejemplos.
- Pronunciación.
- Relaciones.
- Tarjetas.
- Historial de repasos.
- Notas.
- Adjuntos.
- Fuente.

**Acciones:**

- Practicar ahora.
- Editar.
- Suspender.
- Compartir.
- Exportar.
- Eliminar.

---

### S39 — Gestionar tarjetas de un concepto

**Objetivo:** ver y controlar las formas de evaluación.

**Cada tarjeta muestra:**

- Tipo.
- Pregunta.
- Respuesta.
- Estado.
- Próximo repaso.
- Dificultad.
- Último resultado.

**Acciones:**

- Crear tarjeta.
- Activar o desactivar.
- Regenerar.
- Editar plantilla.
- Reiniciar progreso.
- Eliminar tarjeta.

---

### S40 — Crear o editar tarjeta

**Campos:**

- Tipo de ejercicio.
- Cara frontal.
- Cara posterior.
- Pista.
- Explicación.
- Audio.
- Plantilla.
- Dirección:
  - Idioma objetivo → idioma base.
  - Idioma base → idioma objetivo.
- Etiquetas.
- Activar repaso.

---

### S41 — Relaciones entre conceptos

**Objetivo:** conectar vocabulario, reglas, errores y expresiones.

**Tipos de relación:**

- Sinónimo.
- Antónimo.
- Derivado de.
- Relacionado con.
- Confundido con.
- Ejemplo de.
- Excepción de.
- Parte de.
- Contrasta con.

**Visualización:**

- Lista agrupada por relación.
- Vista de red opcional en una versión posterior.

---

## 6.5. Biblioteca y búsqueda

### S50 — Biblioteca global

**Objetivo:** consultar conceptos de todos los idiomas.

**Elementos:**

- Barra de búsqueda.
- Filtros.
- Orden.
- Lista de resultados.
- Selección múltiple.

**Filtros:**

- Idioma.
- Tipo.
- Sección.
- Nivel.
- Estado.
- Dominio.
- Etiqueta.
- Fecha.
- Con adjuntos.
- Con audio.
- Pendiente.

---

### S51 — Búsqueda global

**Comportamiento:**

- Búsqueda por título.
- Traducción.
- Definición.
- Ejemplos.
- Etiquetas.
- Contenido de notas.

**Sugerencias:**

- Consultas recientes.
- Etiquetas frecuentes.
- Conceptos vistos recientemente.

**Resultados agrupados por:**

- Idioma.
- Tipo.
- Relevancia.

---

### S52 — Filtros avanzados

**Objetivo:** construir consultas sin saturar la pantalla principal.

**Controles:**

- Chips.
- Rangos.
- Selectores múltiples.
- Orden.
- Guardar filtro como vista.

---

### S53 — Colecciones

**Objetivo:** agrupar conceptos sin modificar su sección original.

Ejemplos:

- Viaje a Francia.
- Entrevistas de trabajo.
- Verbos irregulares.
- Errores de esta semana.
- Examen B2.

**Funciones:**

- Crear colección.
- Añadir conceptos.
- Ordenar.
- Compartir.
- Exportar.
- Iniciar sesión desde la colección.

---

## 6.6. Importación y exportación

### S60 — Centro de importación y exportación

**Objetivo:** centralizar la portabilidad de datos.

**Opciones:**

- Importar archivo.
- Exportar idioma.
- Exportar colección.
- Copia completa.
- Restaurar copia.
- Historial de importaciones.
- Documentación del formato.

---

### S61 — Seleccionar archivo

**Formatos iniciales:**

- `.json`
- `.langbook.json`

**Posibles formatos futuros:**

- CSV.
- Markdown.
- Anki.
- ZIP con adjuntos.

**Información visible:**

- Formato esperado.
- Enlace a ejemplo.
- Tamaño máximo recomendado.
- Política de privacidad.

---

### S62 — Validación de archivo

**Estados:**

- Leyendo.
- Validando estructura.
- Comprobando versión.
- Analizando conceptos.
- Detectando conflictos.

**No debe bloquear la navegación sin explicación.**

---

### S63 — Vista previa de importación

**Resumen:**

- Idioma.
- Secciones.
- Conceptos.
- Tarjetas.
- Adjuntos.
- Elementos inválidos.
- Advertencias.

**Acciones:**

- Continuar.
- Cancelar.
- Ver errores.
- Cambiar idioma de destino.

---

### S64 — Resolución de conflictos

**Estrategias:**

- Crear.
- Actualizar.
- Omitir.
- Duplicar.
- Preguntar individualmente.

**Cada conflicto muestra:**

- Elemento existente.
- Elemento importado.
- Diferencias.
- Acción seleccionada.

---

### S65 — Resultado de importación

**Contenido:**

- Creados.
- Actualizados.
- Omitidos.
- Duplicados.
- Fallidos.
- Advertencias.
- Enlace al registro.

**Acciones:**

- Abrir contenido importado.
- Exportar informe.
- Volver.

---

### S66 — Configurar exportación

**Opciones:**

- Idioma o colección.
- Incluir progreso.
- Incluir historial.
- Incluir adjuntos.
- Incluir tarjetas suspendidas.
- Anonimizar metadatos.
- Nombre del archivo.
- Destino.

---

### S67 — Historial de importaciones

**Cada registro muestra:**

- Fecha.
- Archivo.
- Resultado.
- Cantidad de conceptos.
- Advertencias.
- Acción para abrir informe.

---

## 6.7. Progreso y estadísticas

### S70 — Resumen de progreso

**Objetivo:** mostrar información accionable, no métricas decorativas.

**Bloques:**

- Retención estimada.
- Tiempo de estudio.
- Conceptos revisados.
- Conceptos nuevos.
- Conceptos débiles.
- Carga de próximos días.
- Rendimiento por tipo de ejercicio.

**Periodo:**

- Semana.
- Mes.
- Tres meses.
- Todo el tiempo.

---

### S71 — Progreso por idioma

**Contenido:**

- Retención.
- Distribución por estado.
- Conceptos por sección.
- Tiempo.
- Errores recurrentes.
- Actividad.
- Próximos repasos.

---

### S72 — Retención y memoria

**Objetivo:** explicar cómo evoluciona el recuerdo.

**Métricas:**

- Probabilidad de recuerdo.
- Estabilidad.
- Dificultad media.
- Lapsos.
- Intervalo medio.
- Tarjetas maduras.

**La pantalla debe incluir explicaciones accesibles, no solo términos técnicos.**

---

### S73 — Historial de estudio

**Cada sesión muestra:**

- Fecha.
- Idiomas.
- Duración.
- Tarjetas.
- Resultado.
- Errores.

**Filtros:**

- Idioma.
- Tipo de ejercicio.
- Fecha.
- Resultado.

---

### S74 — Conceptos débiles

**Objetivo:** convertir estadísticas en acciones.

**Contenido:**

- Conceptos con más lapsos.
- Errores repetidos.
- Tarjetas suspendidas.
- Conceptos sin ejemplos.
- Conceptos con rendimiento desigual.

**Acciones:**

- Crear sesión.
- Editar.
- Añadir ejemplo.
- Cambiar tarjeta.
- Suspender.

---

### S75 — Calendario de actividad

**Objetivo:** mostrar constancia sin castigar.

**Visualización:**

- Días con actividad.
- Duración.
- Idiomas practicados.
- Sesiones.

**No mostrar mensajes negativos por huecos.**

---

## 6.8. Plantillas y automatización

### S80 — Plantillas de concepto

**Objetivo:** acelerar la creación de contenido.

**Plantillas iniciales:**

- Sustantivo.
- Verbo.
- Adjetivo.
- Expresión.
- Regla gramatical.
- Error.
- Pronunciación.

---

### S81 — Editor de plantilla

**Campos configurables:**

- Nombre.
- Tipo.
- Campos visibles.
- Campos obligatorios.
- Orden.
- Tarjetas automáticas.
- Etiquetas predeterminadas.

---

### S82 — Plantillas de tarjetas

**Objetivo:** definir cómo un concepto genera ejercicios.

**Ejemplos:**

- Traducción directa.
- Traducción inversa.
- Cloze.
- Definición.
- Audio.
- Ejemplo incompleto.

---

## 6.9. Ajustes

### S90 — Ajustes generales

**Secciones:**

- Apariencia.
- Estudio.
- Idiomas.
- Notificaciones.
- Datos.
- Accesibilidad.
- Acerca de.

---

### S91 — Apariencia

**Opciones:**

- Seguir sistema.
- Tema claro.
- Tema oscuro.
- Contraste alto.
- Tamaño de texto.
- Densidad.
- Animaciones.
- Uso de color por idioma.

---

### S92 — Ajustes de estudio

**Opciones:**

- Objetivo diario.
- Duración predeterminada.
- Nuevos conceptos por día.
- Límite de repasos.
- Mezcla de ejercicios.
- Orden de sesión.
- Mostrar respuesta automáticamente.
- Intervalos y algoritmo.
- Puntuación de respuesta.

---

### S93 — Notificaciones

**Opciones:**

- Activar.
- Hora.
- Días.
- Solo si existen repasos.
- Recordatorio suave.
- Resumen semanal.

---

### S94 — Datos y copias de seguridad

**Opciones:**

- Exportar copia completa.
- Restaurar.
- Copia automática local.
- Ubicación.
- Historial.
- Eliminar todos los datos.
- Comprobar integridad.

---

### S95 — Accesibilidad

**Opciones:**

- Texto grande.
- Contraste alto.
- Reducir movimiento.
- Lectura en voz alta.
- Respuesta háptica.
- Tiempo adicional.
- No depender del color.

---

### S96 — Acerca de

**Contenido:**

- Versión.
- Licencias.
- Privacidad.
- Formato de archivos.
- Créditos.
- Registro de cambios.
- Reportar problema.

---

## 6.10. Pantallas del sistema

### S100 — Estado vacío genérico

Debe incluir:

- Ilustración simple.
- Título claro.
- Descripción breve.
- Acción principal.
- Acción secundaria opcional.

Nunca debe mostrar únicamente “No hay datos”.

---

### S101 — Error recuperable

**Contenido:**

- Qué ocurrió.
- Qué datos siguen seguros.
- Acción para reintentar.
- Acción alternativa.

---

### S102 — Error de base de datos o recuperación

**Acciones:**

- Reintentar.
- Restaurar copia.
- Exportar datos recuperables.
- Abrir diagnóstico.
- Reiniciar almacenamiento solo con confirmación explícita.

---

### S103 — Confirmación de eliminación

Debe indicar exactamente:

- Qué se elimina.
- Qué elementos relacionados se conservan.
- Si se puede deshacer.
- Si se elimina historial.

Para acciones destructivas graves se exige confirmación reforzada.

---

### S104 — Snackbar y deshacer

Utilizar para:

- Concepto archivado.
- Etiqueta eliminada.
- Sección reordenada.
- Tarjeta suspendida.
- Cambio guardado.

Debe ofrecer `Deshacer` cuando sea posible.

---

# 7. Sistema visual

## 7.1. Dirección visual

La aplicación debe combinar tres referencias conceptuales:

- La serenidad de un cuaderno limpio.
- La precisión de una herramienta de estudio.
- La calidez de un producto personal.

La interfaz debe sentirse moderna, pero no seguir tendencias visuales que reduzcan la legibilidad.

## 7.2. Formas

- Contenedores con esquinas suaves.
- Radios medianos.
- Botones claramente reconocibles.
- Tarjetas planas con borde o elevación mínima.
- Evitar cápsulas excesivas para todo.
- Utilizar chips únicamente para filtros, etiquetas y estados breves.

## 7.3. Densidad

- Densidad media.
- Más aire en pantallas de lectura.
- Densidad ligeramente mayor en listas de conceptos.
- Pantalla de estudio especialmente limpia.

---

# 8. Paleta de color

## 8.1. Concepto cromático

La identidad se basa en:

- **Ámbar suave** como color principal: energía, descubrimiento y progreso.
- **Índigo azulado** como color secundario: reflexión, memoria y profundidad.
- **Verde equilibrado** para éxito y consolidación.
- **Rojo cálido** para errores y acciones destructivas.
- Neutros ligeramente cálidos para evitar una apariencia clínica.

El ámbar debe ser reconocible, pero no chillón.

---

## 8.2. Light mode

| Token | Hex | Uso |
|---|---:|---|
| `color.primary` | `#C96F1A` | Acción principal, FAB, elementos activos |
| `color.onPrimary` | `#FFFFFF` | Texto e iconos sobre primary |
| `color.primaryContainer` | `#FFE2C3` | Fondos destacados |
| `color.onPrimaryContainer` | `#4A2808` | Texto sobre primary container |
| `color.secondary` | `#5364A8` | Acciones secundarias y gráficos |
| `color.onSecondary` | `#FFFFFF` | Texto sobre secondary |
| `color.secondaryContainer` | `#E0E5FF` | Fondos secundarios |
| `color.onSecondaryContainer` | `#17204C` | Texto sobre secondary container |
| `color.tertiary` | `#3F7D6A` | Consolidación, audio, relaciones |
| `color.onTertiary` | `#FFFFFF` | Texto sobre tertiary |
| `color.tertiaryContainer` | `#C8EDDF` | Fondo tertiary |
| `color.onTertiaryContainer` | `#092F26` | Texto sobre tertiary container |
| `color.error` | `#B94A48` | Error y eliminación |
| `color.onError` | `#FFFFFF` | Texto sobre error |
| `color.errorContainer` | `#FFDAD8` | Fondos de error |
| `color.onErrorContainer` | `#410006` | Texto en error container |
| `color.background` | `#FCF8F4` | Fondo general |
| `color.onBackground` | `#211A16` | Texto principal |
| `color.surface` | `#FFFBF8` | Superficies |
| `color.surfaceVariant` | `#F2E8DF` | Superficies secundarias |
| `color.onSurface` | `#211A16` | Texto sobre surface |
| `color.onSurfaceVariant` | `#51453D` | Texto secundario |
| `color.outline` | `#85756A` | Bordes |
| `color.outlineVariant` | `#D8C8BD` | Divisores suaves |
| `color.inverseSurface` | `#372F2A` | Snackbars y superficies inversas |
| `color.inverseOnSurface` | `#FCEFE8` | Texto inverso |
| `color.scrim` | `#000000` | Overlay |

---

## 8.3. Dark mode

| Token | Hex | Uso |
|---|---:|---|
| `color.primary` | `#FFB86B` | Acción principal |
| `color.onPrimary` | `#512900` | Texto sobre primary |
| `color.primaryContainer` | `#743B00` | Fondos destacados |
| `color.onPrimaryContainer` | `#FFE2C3` | Texto sobre primary container |
| `color.secondary` | `#BAC3FF` | Acciones secundarias |
| `color.onSecondary` | `#24306B` | Texto sobre secondary |
| `color.secondaryContainer` | `#3A477F` | Fondos secundarios |
| `color.onSecondaryContainer` | `#E0E5FF` | Texto sobre secondary container |
| `color.tertiary` | `#AAD1C2` | Consolidación y relaciones |
| `color.onTertiary` | `#12382E` | Texto sobre tertiary |
| `color.tertiaryContainer` | `#2A5045` | Fondo tertiary |
| `color.onTertiaryContainer` | `#C8EDDF` | Texto sobre tertiary container |
| `color.error` | `#FFB4AB` | Error |
| `color.onError` | `#690005` | Texto sobre error |
| `color.errorContainer` | `#93000A` | Fondo de error |
| `color.onErrorContainer` | `#FFDAD6` | Texto sobre error container |
| `color.background` | `#19120E` | Fondo general |
| `color.onBackground` | `#F0DFD6` | Texto principal |
| `color.surface` | `#211914` | Superficies |
| `color.surfaceVariant` | `#51453D` | Superficies secundarias |
| `color.onSurface` | `#F0DFD6` | Texto |
| `color.onSurfaceVariant` | `#D8C8BD` | Texto secundario |
| `color.outline` | `#A08E82` | Bordes |
| `color.outlineVariant` | `#51453D` | Divisores |
| `color.inverseSurface` | `#F0DFD6` | Superficie inversa |
| `color.inverseOnSurface` | `#372F2A` | Texto inverso |
| `color.scrim` | `#000000` | Overlay |

---

## 8.4. Colores semánticos

| Estado | Light | Dark |
|---|---:|---:|
| Nuevo | `#5364A8` | `#BAC3FF` |
| Aprendiendo | `#C96F1A` | `#FFB86B` |
| Repaso | `#7B5DA7` | `#D5B8FF` |
| Fuerte | `#3F7D6A` | `#AAD1C2` |
| Reaprendiendo | `#B65E3C` | `#FFB59A` |
| Suspendido | `#6E625A` | `#BFB2AA` |
| Correcto | `#3F7D6A` | `#AAD1C2` |
| Difícil | `#B7791F` | `#F5C06A` |
| Incorrecto | `#B94A48` | `#FFB4AB` |
| Información | `#5364A8` | `#BAC3FF` |

---

## 8.5. Colores por idioma

El usuario puede elegir un color identificativo por idioma. Este color no sustituye la paleta principal; se utiliza como acento contextual.

Paleta recomendada:

```text
Amber      #C96F1A
Indigo     #5364A8
Teal       #3F7D6A
Rose       #A8546E
Purple     #7B5DA7
Blue       #3D6F9E
Olive      #697447
Terracotta #A85D43
```

Reglas:

- El color del idioma se usa en iconos, cabeceras y pequeños indicadores.
- No debe utilizarse como fondo de toda la pantalla.
- Debe existir una variante accesible en dark mode.
- El usuario puede cambiarlo sin afectar al progreso.

---

# 9. Tokens de diseño

## 9.1. Tipografía

Fuente principal recomendada:

```text
Inter
```

Alternativas:

```text
Roboto
Manrope
Source Sans 3
```

Para contenido fonético o ejemplos especiales puede utilizarse una fuente secundaria compatible con IPA, pero solo cuando sea necesario.

### Escala tipográfica

| Token | Tamaño | Altura de línea | Peso | Uso |
|---|---:|---:|---:|---|
| `type.displayLarge` | 40sp | 48sp | 600 | Métricas grandes |
| `type.displayMedium` | 34sp | 42sp | 600 | Titulares destacados |
| `type.headlineLarge` | 30sp | 38sp | 600 | Título de screen |
| `type.headlineMedium` | 26sp | 34sp | 600 | Sección principal |
| `type.titleLarge` | 22sp | 28sp | 600 | Tarjetas y diálogos |
| `type.titleMedium` | 18sp | 24sp | 600 | Subtítulos |
| `type.bodyLarge` | 16sp | 24sp | 400 | Texto principal |
| `type.bodyMedium` | 14sp | 20sp | 400 | Texto secundario |
| `type.bodySmall` | 12sp | 16sp | 400 | Metadatos |
| `type.labelLarge` | 14sp | 20sp | 600 | Botones |
| `type.labelMedium` | 12sp | 16sp | 600 | Chips |
| `type.labelSmall` | 11sp | 14sp | 600 | Etiquetas compactas |

### Reglas

- Nunca utilizar menos de 11sp.
- El texto principal de estudio debe ser mayor que el texto normal.
- Las palabras objetivo pueden usar entre 28sp y 40sp.
- Las traducciones y ejemplos deben mantener una jerarquía clara.

---

## 9.2. Espaciado

Sistema base de 4dp:

| Token | Valor |
|---|---:|
| `space.0` | 0dp |
| `space.1` | 4dp |
| `space.2` | 8dp |
| `space.3` | 12dp |
| `space.4` | 16dp |
| `space.5` | 20dp |
| `space.6` | 24dp |
| `space.8` | 32dp |
| `space.10` | 40dp |
| `space.12` | 48dp |
| `space.16` | 64dp |

Uso recomendado:

- Margen horizontal de screen: `16dp`.
- Tablets: `24dp` o `32dp`.
- Separación entre secciones: `24dp`.
- Separación entre elementos relacionados: `8dp`.
- Padding interno de tarjetas: `16dp`.

---

## 9.3. Radios

| Token | Valor | Uso |
|---|---:|---|
| `radius.none` | 0dp | Divisores |
| `radius.xs` | 4dp | Indicadores |
| `radius.sm` | 8dp | Campos compactos |
| `radius.md` | 12dp | Tarjetas |
| `radius.lg` | 16dp | Diálogos y paneles |
| `radius.xl` | 24dp | Tarjetas destacadas |
| `radius.full` | 999dp | Chips y avatares |

---

## 9.4. Elevación

| Token | Valor | Uso |
|---|---:|---|
| `elevation.none` | 0dp | Listas |
| `elevation.low` | 1dp | Tarjetas |
| `elevation.medium` | 3dp | Barra inferior |
| `elevation.high` | 6dp | Menús |
| `elevation.modal` | 12dp | Diálogos |

Priorizar bordes y diferencias de superficie sobre sombras intensas.

---

## 9.5. Tamaños táctiles

| Token | Valor |
|---|---:|
| `touch.minimum` | 48dp |
| `icon.small` | 16dp |
| `icon.medium` | 24dp |
| `icon.large` | 32dp |
| `button.height` | 48dp |
| `button.largeHeight` | 56dp |
| `fab.size` | 56dp |

---

## 9.6. Opacidad

| Token | Valor |
|---|---:|
| `opacity.disabled` | 0.38 |
| `opacity.secondary` | 0.72 |
| `opacity.overlay` | 0.60 |
| `opacity.divider` | 0.12 |
| `opacity.hover` | 0.08 |
| `opacity.pressed` | 0.12 |

---

## 9.7. Movimiento

| Token | Duración | Uso |
|---|---:|---|
| `motion.fast` | 100ms | Feedback inmediato |
| `motion.short` | 180ms | Cambios de estado |
| `motion.medium` | 280ms | Navegación |
| `motion.long` | 420ms | Expansión compleja |

Curvas:

```text
motion.standard = cubic-bezier(0.2, 0, 0, 1)
motion.emphasized = cubic-bezier(0.2, 0, 0, 1)
motion.exit = cubic-bezier(0.4, 0, 1, 1)
```

Reglas:

- Las animaciones deben explicar cambios.
- Evitar movimiento continuo.
- Respetar `Reduce motion`.
- No usar confeti frecuente.
- El feedback correcto puede utilizar una transición sutil de borde y escala.

---

# 10. Componentes principales

## 10.1. Botón principal

Uso:

- Guardar.
- Empezar.
- Continuar.
- Importar.

Características:

- Fondo `primary`.
- Texto `onPrimary`.
- Altura mínima 48dp.
- Estado loading.
- Estado disabled claramente diferenciado.

---

## 10.2. Botón secundario

- Borde o `secondaryContainer`.
- No competir visualmente con la acción principal.

---

## 10.3. Tarjeta de idioma

Contenido:

- Icono o abreviatura.
- Nombre.
- Progreso.
- Pendientes.
- Metadatos.
- Menú.

Estados:

- Normal.
- Seleccionada.
- Archivada.
- Con pendientes.
- Sin actividad.

---

## 10.4. Tarjeta de concepto

Contenido:

- Tipo.
- Título.
- Significado breve.
- Etiquetas.
- Estado.
- Próximo repaso.

No debe mostrar toda la información del concepto.

---

## 10.5. Chip

Tipos:

- Filtro.
- Etiqueta.
- Estado.
- Selección.
- Acción compacta.

Debe mantener un área táctil accesible.

---

## 10.6. Campo de texto

Variantes:

- Una línea.
- Multilínea.
- Búsqueda.
- Respuesta de estudio.
- Con prefijo o sufijo.
- Con contador.

Estados:

- Normal.
- Focus.
- Error.
- Correcto.
- Desactivado.
- Solo lectura.

---

## 10.7. Indicador de dominio

No utilizar solo porcentaje.

Ejemplo:

```text
Fuerte · 82%
```

El estado incluye:

- Etiqueta.
- Color.
- Icono.
- Valor opcional.

---

## 10.8. Barra de progreso de sesión

Debe comunicar:

- Posición actual.
- Total.
- Segmentos opcionales.
- Sin generar presión.

---

## 10.9. Bottom sheet

Usos:

- Filtros.
- Captura rápida.
- Acciones de concepto.
- Selección de sección.
- Configuración de sesión.

---

## 10.10. Diálogo

Reservado para:

- Eliminaciones.
- Conflictos.
- Errores graves.
- Confirmaciones que no pueden deshacerse.

---

# 11. Reglas de contenido y microcopy

## 11.1. Tono

- Claro.
- Directo.
- Respetuoso.
- Motivador sin exageración.
- Sin infantilización.
- Sin culpabilización.

## 11.2. Ejemplos correctos

```text
Tienes 18 tarjetas listas para repasar.
```

```text
Has reforzado 9 conceptos.
```

```text
Este concepto necesita más práctica.
```

## 11.3. Ejemplos a evitar

```text
¡Has fallado otra vez!
```

```text
Perdiste tu racha.
```

```text
No estudiaste ayer.
```

```text
¡Increíble! ¡Eres imparable!
```

## 11.4. Errores

Los mensajes deben indicar:

1. Qué ocurrió.
2. Qué se puede hacer.
3. Si los datos están seguros.

Ejemplo:

> No se pudo importar el archivo porque utiliza una versión no compatible. Tus datos actuales no se han modificado.

---

# 12. Accesibilidad

## 12.1. Contraste

- Cumplir WCAG AA.
- Texto normal mínimo 4.5:1.
- Texto grande mínimo 3:1.
- Estados no dependientes únicamente del color.

## 12.2. Tamaño

- Targets de al menos 48dp.
- Soporte de escalado de fuente.
- Evitar truncar palabras importantes.

## 12.3. Lectores de pantalla

Cada control debe tener:

- Etiqueta.
- Rol.
- Estado.
- Acción.
- Descripción cuando sea necesaria.

## 12.4. Movimiento

- Opción para reducir animaciones.
- No utilizar parpadeos.
- No depender de gestos complejos.

## 12.5. Audio

- Todo audio debe tener alternativa textual.
- Los ejercicios auditivos deben poder repetirse.
- Control de velocidad en una versión posterior.

## 12.6. Daltonismo

- Estados acompañados de iconos y texto.
- Evitar combinaciones rojo-verde como único contraste.

---

# 13. Comportamiento responsive

## Móvil compacto

- Navegación inferior.
- Una columna.
- Bottom sheets.
- Tarjetas apiladas.

## Móvil grande

- Una columna con mayor anchura.
- Formularios con agrupaciones visuales.

## Tablet

- Navigation rail.
- Diseño maestro-detalle.
- Lista de conceptos a la izquierda.
- Detalle a la derecha.
- Formularios en dos columnas cuando tenga sentido.

## Plegables

- Adaptar a postura.
- Evitar elementos importantes en la bisagra.
- Usar panel doble para biblioteca y detalle.

---

# 14. Reglas de implementación en Jetpack Compose

## 14.1. Tema

Crear un `LanguesTheme` con:

```kotlin
LanguesTheme(
    darkTheme = isSystemInDarkTheme(),
    dynamicColor = false,
    content = content
)
```

El color dinámico puede añadirse después como opción, pero no debe sustituir la identidad inicial.

## 14.2. Tokens

Centralizar:

```text
Color.kt
Type.kt
Shape.kt
Spacing.kt
Elevation.kt
Motion.kt
```

## 14.3. Componentes

Crear componentes reutilizables:

```text
LanguesButton
LanguesOutlinedButton
LanguageCard
ConceptCard
MasteryBadge
ReviewRatingBar
EmptyState
ErrorState
SectionHeader
FilterChipGroup
SearchBar
StudyProgress
```

## 14.4. Previews

Cada componente y screen debe tener previews de:

- Light mode.
- Dark mode.
- Texto grande.
- Estado vacío.
- Estado cargado.
- Estado de error.

---

# 15. Priorización de pantallas

## MVP imprescindible

```text
S01 Splash
S02 Bienvenida
S03 Crear primer idioma
S10 Hoy
S11 Selector de sesión
S12 Sesión de estudio
S14 Resumen de sesión
S20 Lista de idiomas
S21 Crear idioma
S22 Detalle del idioma
S25 Detalle de sección
S30 Captura rápida
S32 Vocabulario
S33 Expresión
S34 Gramática
S36 Error
S38 Detalle de concepto
S39 Tarjetas del concepto
S50 Biblioteca global
S51 Búsqueda
S60 Importar y exportar
S61 Seleccionar archivo
S63 Vista previa
S65 Resultado
S70 Resumen de progreso
S90 Ajustes
S91 Apariencia
S92 Estudio
S94 Datos y copias
S100 Estado vacío
S101 Error recuperable
S103 Confirmación de eliminación
```

## Segunda fase

```text
S24 Configurar secciones
S35 Pronunciación
S37 Nota libre
S40 Editor de tarjeta
S41 Relaciones
S53 Colecciones
S64 Conflictos
S67 Historial de importaciones
S71 Progreso por idioma
S72 Retención
S73 Historial
S74 Conceptos débiles
S80 Plantillas
S81 Editor de plantilla
S82 Plantillas de tarjetas
S93 Notificaciones
S95 Accesibilidad
```

## Fase avanzada

```text
Dictado avanzado
Reconocimiento de voz
Sincronización
Importación Anki
Generación asistida
Vista de red
Colaboración
Bibliotecas compartidas
```

---

# 16. Criterios de éxito de la interfaz

La experiencia puede considerarse correcta cuando:

- Crear una palabra básica requiere menos de 20 segundos.
- Comenzar un repaso requiere uno o dos toques desde Hoy.
- El usuario puede encontrar un concepto mediante búsqueda en pocos segundos.
- La pantalla de estudio no contiene elementos innecesarios.
- El usuario entiende por qué un concepto vuelve a aparecer.
- Importar un archivo muestra una vista previa antes de modificar datos.
- El progreso se comunica sin culpa ni presión.
- Light y dark mode mantienen la misma jerarquía.
- Todas las acciones destructivas son claras y reversibles cuando sea posible.
- El usuario puede exportar sus datos sin crear una cuenta.

---

# 17. Resumen de dirección de diseño

Langues debe sentirse como:

```text
Un cuaderno personal
+ una herramienta de memoria
+ un sistema de práctica
+ una biblioteca que crece contigo
```

La identidad visual debe ser:

- Cálida.
- Sobria.
- Clara.
- Profunda.
- Modular.
- Accesible.
- Personalizable.
- Orientada a la práctica.

La interfaz no debe competir con el contenido. El idioma, los ejemplos y la respuesta del usuario son siempre los protagonistas.

---

# 18. Nombre recomendado para el sistema de diseño

```text
Langues Design System
```

Abreviatura interna:

```text
LDS
```

Estructura sugerida:

```text
foundation/
  colors
  typography
  spacing
  shapes
  motion

components/
  actions
  inputs
  navigation
  feedback
  content
  study

patterns/
  empty-states
  forms
  study-session
  import-flow
  concept-detail
```

---

# 19. Próximo paso recomendado

Antes de implementar todas las pantallas, conviene crear un prototipo navegable con estas seis:

1. Hoy.
2. Detalle de idioma.
3. Lista de sección.
4. Crear concepto.
5. Sesión de estudio.
6. Resumen de sesión.

Estas pantallas validan la mayor parte del producto: captura, organización, práctica y progreso.
