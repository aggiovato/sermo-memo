# SermoMemo

## Documento maestro de producto, arquitectura, UX, diseño y negocio

> Aplicación móvil local-first para capturar, organizar, practicar y consolidar conocimiento de idiomas mediante un notebook estructurado, práctica activa y repetición espaciada.

---

# 1. Resumen ejecutivo

**SermoMemo** es una aplicación móvil para el aprendizaje activo de idiomas. Combina las capacidades de un cuaderno personal, una base de conocimiento lingüístico, un sistema de flashcards, un motor de repetición espaciada y una biblioteca portable de contenido.

La aplicación permite al usuario:

- Crear uno o varios idiomas de estudio.
- Organizar cada idioma en secciones personalizables.
- Registrar vocabulario, gramática, expresiones, pronunciación, errores y notas.
- Convertir conceptos en diferentes tipos de ejercicios.
- Repasar mediante repetición espaciada.
- Consultar estadísticas útiles.
- Importar y exportar contenido.
- Trabajar completamente offline en la primera versión.
- Mantener la propiedad y portabilidad de sus datos.

La primera versión se desarrollará con:

```text
Kotlin
Jetpack Compose
Room
Coroutines
Flow
Kotlin Serialization
DataStore
WorkManager
```

La arquitectura será local-first y seguirá una organización hexagonal con separación entre dominio, casos de uso, puertos, adaptadores, infraestructura y presentación.

---

# 2. Identidad del producto

## 2.1. Nombre

```text
SermoMemo
```

### Significado

- `Sermo`: conversación, discurso, forma de hablar o lenguaje.
- `Memo`: memoria, recordatorio y nota.

El nombre conecta las tres funciones esenciales del producto:

```text
Idioma
+ notas
+ memoria
```

## 2.2. Descripción breve

> SermoMemo es tu notebook personal para aprender idiomas, convertir apuntes en práctica y recordar lo que estudias.

## 2.3. Descripción extendida

SermoMemo permite construir una biblioteca personal de idiomas. Cada palabra, expresión, regla gramatical o error puede guardarse como un concepto estructurado y transformarse en distintas actividades de práctica.

La aplicación no se limita a mostrar tarjetas. Busca acompañar todo el proceso:

```text
Descubrir
→ capturar
→ organizar
→ comprender
→ practicar
→ repasar
→ consolidar
```

## 2.4. Promesa de producto

> Todo lo que aprendes puede convertirse en algo que puedes practicar.

---

# 3. Problema que resuelve

Las herramientas de aprendizaje de idiomas suelen estar fragmentadas:

- Las aplicaciones de notas permiten guardar contenido, pero no repasarlo.
- Las flashcards permiten memorizar, pero ofrecen poco contexto.
- Los cursos estructurados limitan la personalización.
- Los diccionarios permiten consultar, pero no construir conocimiento personal.
- Las aplicaciones de repetición espaciada pueden resultar técnicas o poco visuales.
- Los apuntes, capturas, audios y errores suelen terminar dispersos.

SermoMemo centraliza esos flujos en una única experiencia.

## 3.1. Problemas principales

### Fragmentación

El usuario guarda palabras en una app, reglas en otra, capturas en una carpeta y flashcards en otra herramienta.

### Falta de práctica activa

Guardar información no implica poder recordarla ni utilizarla.

### Ausencia de contexto

Una palabra aislada se aprende peor que una palabra acompañada de ejemplos, relaciones y registro de uso.

### Herramientas demasiado rígidas

Cada idioma requiere estructuras distintas. Una app útil debe permitir personalización.

### Pérdida de propiedad

Muchas plataformas hacen difícil exportar, migrar o reutilizar el contenido.

---

# 4. Visión y valores

## 4.1. Visión

Convertirse en el espacio personal donde una persona construye y consolida su conocimiento lingüístico a lo largo del tiempo.

## 4.2. Valores

### Aprendizaje activo

El contenido se escribe, produce, corrige, escucha y repasa.

### Propiedad de los datos

La información pertenece al usuario y puede exportarse.

### Flexibilidad

Cada idioma puede organizarse de forma distinta.

### Progreso honesto

El dominio se calcula a partir de la práctica real.

### Serenidad

La aplicación ayuda sin culpabilizar ni castigar.

### Profundidad progresiva

Las funciones avanzadas aparecen cuando aportan valor.

### Privacidad

La primera versión funciona sin cuenta y sin conexión.

### Accesibilidad

La experiencia debe ser usable con diferentes capacidades visuales, motoras y cognitivas.

---

# 5. Público objetivo

## 5.1. Usuario principal

Persona que aprende uno o varios idiomas y desea registrar contenido propio.

Características:

- Estudia de forma autónoma.
- Consume vídeos, podcasts, libros o clases.
- Encuentra vocabulario y expresiones fuera de un curso tradicional.
- Quiere guardar y practicar sus propios conceptos.
- Valora la personalización.
- Puede utilizar uno o varios idiomas simultáneamente.

## 5.2. Perfiles secundarios

### Estudiante académico

Prepara exámenes, certificaciones o asignaturas.

### Profesional

Necesita vocabulario especializado para trabajo, entrevistas o reuniones.

### Profesor

Crea colecciones y materiales para alumnos.

### Familia o grupo

Comparte colecciones, listas y objetivos comunes.

### Políglota

Administra varios idiomas con diferentes niveles y estrategias.

---

# 6. Principios del producto

## 6.1. Un concepto no es una tarjeta

El contenido lingüístico, las tarjetas y el historial de aprendizaje deben estar separados.

```text
Concepto
  ↓
Una o varias tarjetas
  ↓
Estado e historial de repaso
```

Ejemplo:

```text
Concepto:
pomme

Tarjetas:
pomme → manzana
manzana → pomme
Je mange une ____.
audio → escribir palabra
```

## 6.2. Room es la fuente local de verdad

La primera versión debe funcionar sin servidor.

## 6.3. El historial no se sobrescribe

Cada repaso genera un evento inmutable.

## 6.4. El algoritmo debe ser reemplazable

La lógica de repetición espaciada se expone mediante un puerto.

## 6.5. La importación debe ser segura

Antes de modificar datos debe existir una vista previa y resolución de conflictos.

## 6.6. La interfaz no compite con el contenido

Durante el estudio, el idioma y la respuesta del usuario son los protagonistas.

---

# 7. Alcance funcional general

## 7.1. Gestión de idiomas

- Crear idioma.
- Editar idioma.
- Archivar idioma.
- Eliminar idioma.
- Asignar color.
- Asignar código.
- Definir idioma base.
- Elegir nivel.
- Copiar estructura.
- Exportar idioma.
- Importar contenido.

## 7.2. Gestión de secciones

- Crear.
- Editar.
- Reordenar.
- Ocultar.
- Archivar.
- Eliminar.
- Crear subsecciones.
- Definir si genera práctica.
- Definir icono y color contextual.

## 7.3. Gestión de conceptos

Tipos iniciales:

- Vocabulario.
- Expresión.
- Gramática.
- Error.
- Pronunciación.
- Nota.
- Referencia.

## 7.4. Práctica

- Reconocimiento.
- Producción.
- Cloze.
- Corrección de errores.
- Ordenar frase.
- Opción múltiple.
- Dictado.
- Producción libre.
- Pronunciación.

## 7.5. Repetición espaciada

- Conceptos nuevos.
- En aprendizaje.
- En repaso.
- Fuertes.
- Reaprendizaje.
- Suspendidos.

## 7.6. Importación y exportación

- JSON versionado.
- Copias completas.
- Exportación por idioma.
- Exportación por colección.
- Historial de importaciones.
- Conflictos.
- Validación.
- Informe de resultados.

## 7.7. Estadísticas

- Retención.
- Conceptos revisados.
- Tiempo de estudio.
- Errores recurrentes.
- Rendimiento por idioma.
- Rendimiento por sección.
- Rendimiento por tipo de ejercicio.
- Próxima carga de repaso.

---

# 8. Dominios funcionales

La aplicación se divide en dominios de negocio claros.

```text
Language
Notebook
Concept
Card
Review
Practice
ImportExport
Statistics
Collection
Settings
Subscription
Group
Sync
```

---

# 9. Dominio Language

## 9.1. Responsabilidad

Gestiona los espacios de aprendizaje por idioma.

## 9.2. Entidad

```kotlin
data class Language(
    val id: LanguageId,
    val name: String,
    val code: String?,
    val baseLanguageCode: String?,
    val level: LanguageLevel?,
    val colorToken: String,
    val icon: String?,
    val status: LanguageStatus,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

## 9.3. Enumeraciones

```kotlin
enum class LanguageStatus {
    ACTIVE,
    ARCHIVED
}
```

```kotlin
enum class LanguageLevel {
    BEGINNER,
    A1,
    A2,
    B1,
    B2,
    C1,
    C2,
    NATIVE,
    CUSTOM
}
```

## 9.4. Reglas

- Un idioma activo debe tener al menos una sección.
- El nombre es obligatorio.
- El idioma base es opcional.
- Archivar no elimina datos.
- Eliminar requiere confirmación reforzada.
- El límite de idiomas puede depender del plan.

---

# 10. Dominio Notebook

## 10.1. Responsabilidad

Gestiona la estructura organizativa de cada idioma.

## 10.2. Entidad

```kotlin
data class Section(
    val id: SectionId,
    val languageId: LanguageId,
    val parentSectionId: SectionId?,
    val name: String,
    val type: SectionType,
    val icon: String?,
    val colorToken: String?,
    val position: Int,
    val practiceEnabled: Boolean,
    val visibility: SectionVisibility,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

## 10.3. Tipos de sección

```kotlin
enum class SectionType {
    VOCABULARY,
    GRAMMAR,
    EXPRESSIONS,
    PRONUNCIATION,
    ERRORS,
    NOTES,
    CUSTOM
}
```

## 10.4. Reglas

- La posición es única dentro del mismo nivel.
- Una sección puede tener subsecciones.
- Ocultar una sección no elimina conceptos.
- Una sección puede ser solo informativa.
- No se permiten ciclos entre secciones.

---

# 11. Dominio Concept

## 11.1. Responsabilidad

Representa conocimiento lingüístico estructurado.

## 11.2. Entidad principal

```kotlin
data class Concept(
    val id: ConceptId,
    val externalId: String?,
    val languageId: LanguageId,
    val sectionId: SectionId?,
    val type: ConceptType,
    val title: String,
    val meaning: String?,
    val explanation: String?,
    val notes: String?,
    val source: String?,
    val level: ConceptLevel?,
    val status: ConceptStatus,
    val attributes: ConceptAttributes,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

## 11.3. Tipos

```kotlin
enum class ConceptType {
    VOCABULARY,
    EXPRESSION,
    GRAMMAR,
    PRONUNCIATION,
    ERROR,
    NOTE,
    TEXT,
    REFERENCE
}
```

## 11.4. Estado

```kotlin
enum class ConceptStatus {
    ACTIVE,
    ARCHIVED,
    SUSPENDED
}
```

## 11.5. Atributos especializados

Para el MVP se recomienda una combinación de columnas comunes y atributos serializados.

```kotlin
@Serializable
sealed interface ConceptAttributes
```

Ejemplos:

```kotlin
@Serializable
data class VocabularyAttributes(
    val partOfSpeech: String? = null,
    val gender: String? = null,
    val plural: String? = null,
    val pronunciation: String? = null,
    val register: String? = null,
    val region: String? = null
) : ConceptAttributes
```

```kotlin
@Serializable
data class GrammarAttributes(
    val pattern: String? = null,
    val exceptions: List<String> = emptyList()
) : ConceptAttributes
```

```kotlin
@Serializable
data class ErrorAttributes(
    val incorrectForm: String,
    val correctedForm: String,
    val sourceType: String?,
    val occurrenceCount: Int
) : ConceptAttributes
```

## 11.6. Elementos relacionados

```kotlin
data class Example(
    val id: ExampleId,
    val conceptId: ConceptId,
    val targetText: String,
    val translation: String?,
    val notes: String?,
    val position: Int
)
```

```kotlin
data class Tag(
    val id: TagId,
    val languageId: LanguageId?,
    val name: String
)
```

```kotlin
data class Attachment(
    val id: AttachmentId,
    val conceptId: ConceptId,
    val type: AttachmentType,
    val uri: String,
    val mimeType: String,
    val displayName: String?,
    val sizeBytes: Long?,
    val checksum: String?
)
```

## 11.7. Relaciones

```kotlin
data class ConceptRelation(
    val id: ConceptRelationId,
    val sourceConceptId: ConceptId,
    val targetConceptId: ConceptId,
    val type: ConceptRelationType
)
```

```kotlin
enum class ConceptRelationType {
    SYNONYM,
    ANTONYM,
    DERIVED_FROM,
    RELATED_TO,
    CONFUSED_WITH,
    EXAMPLE_OF,
    EXCEPTION_OF,
    PART_OF,
    CONTRASTS_WITH
}
```

---

# 12. Dominio Card

## 12.1. Responsabilidad

Representa una forma concreta de evaluar un concepto.

## 12.2. Entidad

```kotlin
data class Card(
    val id: CardId,
    val conceptId: ConceptId,
    val type: CardType,
    val front: String,
    val back: String,
    val hint: String?,
    val explanation: String?,
    val direction: CardDirection?,
    val enabled: Boolean,
    val templateId: CardTemplateId?,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

## 12.3. Tipos

```kotlin
enum class CardType {
    RECOGNITION,
    PRODUCTION,
    CLOZE,
    MULTIPLE_CHOICE,
    SENTENCE_ORDER,
    ERROR_CORRECTION,
    DICTATION,
    FREE_PRODUCTION,
    PRONUNCIATION
}
```

```kotlin
enum class CardDirection {
    TARGET_TO_BASE,
    BASE_TO_TARGET,
    BIDIRECTIONAL
}
```

## 12.4. Reglas

- Un concepto puede tener cero o muchas tarjetas.
- Una tarjeta puede desactivarse sin perder historial.
- Cambiar el contenido no reinicia automáticamente el progreso.
- El reinicio debe ser explícito.
- Las tarjetas generadas pueden editarse.

---

# 13. Dominio Review

## 13.1. Responsabilidad

Gestiona el estado de memoria y la planificación.

## 13.2. Estado

```kotlin
data class ReviewState(
    val cardId: CardId,
    val dueAt: Instant,
    val lastReviewedAt: Instant?,
    val status: LearningStatus,
    val stability: Double,
    val difficulty: Double,
    val repetitions: Int,
    val lapses: Int,
    val lastIntervalDays: Int,
    val nextIntervalDays: Int
)
```

```kotlin
enum class LearningStatus {
    NEW,
    LEARNING,
    REVIEWING,
    STRONG,
    RELEARNING,
    SUSPENDED
}
```

## 13.3. Evento de repaso

```kotlin
data class ReviewLog(
    val id: ReviewLogId,
    val cardId: CardId,
    val sessionId: StudySessionId?,
    val rating: ReviewRating,
    val reviewedAt: Instant,
    val responseTimeMs: Long?,
    val wasCorrect: Boolean?,
    val previousIntervalDays: Int,
    val nextIntervalDays: Int,
    val previousStability: Double?,
    val nextStability: Double?,
    val previousDifficulty: Double?,
    val nextDifficulty: Double?
)
```

```kotlin
enum class ReviewRating {
    AGAIN,
    HARD,
    GOOD,
    EASY
}
```

## 13.4. Puerto del algoritmo

```kotlin
interface ReviewScheduler {
    fun schedule(
        state: ReviewState,
        rating: ReviewRating,
        reviewedAt: Instant
    ): ReviewScheduleResult
}
```

Esto permite empezar con un algoritmo sencillo y adoptar FSRS posteriormente.

---

# 14. Dominio Practice

## 14.1. Responsabilidad

Orquesta sesiones de estudio.

## 14.2. Entidades

```kotlin
data class StudySession(
    val id: StudySessionId,
    val languageIds: Set<LanguageId>,
    val startedAt: Instant,
    val completedAt: Instant?,
    val plannedCardCount: Int,
    val completedCardCount: Int,
    val mode: StudyMode,
    val status: StudySessionStatus
)
```

```kotlin
enum class StudyMode {
    SMART,
    WEAK_ONLY,
    RANDOM,
    SECTION,
    COLLECTION,
    ERRORS_ONLY
}
```

```kotlin
enum class StudySessionStatus {
    ACTIVE,
    PAUSED,
    COMPLETED,
    ABANDONED
}
```

## 14.3. Configuración

```kotlin
data class StudySessionConfig(
    val languageIds: Set<LanguageId>,
    val sectionIds: Set<SectionId>,
    val maxCards: Int?,
    val maxDurationMinutes: Int?,
    val includeNew: Boolean,
    val includeOverdue: Boolean,
    val cardTypes: Set<CardType>,
    val mode: StudyMode
)
```

---

# 15. Dominio ImportExport

## 15.1. Responsabilidad

Gestiona portabilidad, validación, conflictos y copias.

## 15.2. Modelo de importación

```kotlin
data class ImportBatch(
    val id: ImportBatchId,
    val sourceName: String,
    val formatVersion: Int,
    val startedAt: Instant,
    val finishedAt: Instant?,
    val status: ImportStatus,
    val createdCount: Int,
    val updatedCount: Int,
    val skippedCount: Int,
    val failedCount: Int
)
```

```kotlin
enum class ImportStatus {
    VALIDATING,
    PREVIEW_READY,
    IMPORTING,
    COMPLETED,
    COMPLETED_WITH_WARNINGS,
    FAILED,
    CANCELLED
}
```

## 15.3. Conflictos

```kotlin
enum class ImportConflictStrategy {
    CREATE,
    UPDATE,
    SKIP,
    DUPLICATE,
    ASK
}
```

## 15.4. Reglas

- Toda importación debe validarse.
- Debe existir vista previa.
- Debe ejecutarse en transacción.
- Nunca se elimina historial de repaso por defecto.
- `externalId` permite actualización incremental.
- El formato debe estar versionado.
- Los errores deben registrarse.

---

# 16. Dominio Collection

## 16.1. Responsabilidad

Permite agrupar conceptos sin modificar su sección.

```kotlin
data class Collection(
    val id: CollectionId,
    val name: String,
    val description: String?,
    val languageId: LanguageId?,
    val colorToken: String?,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

Ejemplos:

- Viaje a Francia.
- Entrevista de trabajo.
- Verbos irregulares.
- Examen B2.
- Errores de esta semana.

---

# 17. Dominio Statistics

## 17.1. Responsabilidad

Calcula métricas útiles a partir de eventos.

## 17.2. Métricas

- Retención estimada.
- Número de conceptos.
- Tarjetas maduras.
- Lapsos.
- Tiempo.
- Respuestas por rating.
- Distribución por estado.
- Conceptos débiles.
- Próxima carga de repaso.
- Rendimiento por ejercicio.
- Rendimiento por sección.

## 17.3. Regla

Las estadísticas se derivan de datos históricos. No deben ser la fuente de verdad del dominio.

---

# 18. Dominio Subscription

## 18.1. Responsabilidad

Gestiona límites, derechos y planes comerciales.

```kotlin
data class Entitlement(
    val feature: PremiumFeature,
    val enabled: Boolean,
    val limit: Int?
)
```

```kotlin
enum class PremiumFeature {
    MAX_LANGUAGES,
    CLOUD_SYNC,
    AUTOMATIC_BACKUP,
    SHARED_COLLECTIONS,
    GROUP_MANAGEMENT,
    ADVANCED_STATISTICS,
    AI_ASSISTANCE,
    AUDIO_STORAGE,
    IMPORT_FORMATS,
    CUSTOM_TEMPLATES
}
```

## 18.2. Principio

Los datos locales nunca deben quedar bloqueados al finalizar una suscripción.

El usuario debe poder:

- Consultar sus idiomas.
- Exportar sus datos.
- Seguir usando contenido existente.
- Mantener su historial.

Las limitaciones deben aplicarse principalmente a creación adicional, sincronización o funciones premium.

---

# 19. Dominio Group

## 19.1. Responsabilidad

Gestiona espacios compartidos para familias, profesores, academias y grupos de estudio.

```kotlin
data class Group(
    val id: GroupId,
    val name: String,
    val ownerId: UserId,
    val plan: GroupPlan,
    val createdAt: Instant,
    val updatedAt: Instant
)
```

```kotlin
data class GroupMember(
    val groupId: GroupId,
    val userId: UserId,
    val role: GroupRole,
    val joinedAt: Instant
)
```

```kotlin
enum class GroupRole {
    OWNER,
    ADMIN,
    TEACHER,
    MEMBER,
    VIEWER
}
```

## 19.2. Funciones futuras

- Bibliotecas compartidas.
- Colecciones asignadas.
- Progreso agregado.
- Comentarios.
- Permisos.
- Invitaciones.
- Duplicación de materiales.

---

# 20. Arquitectura hexagonal

## 20.1. Objetivo

Separar las reglas del negocio de Android, Room, Compose, servicios externos y proveedores de pago.

```text
Presentation
    ↓
Application
    ↓
Domain
    ↕
Ports
    ↕
Adapters
    ↓
Infrastructure
```

## 20.2. Capas

### Domain

Contiene:

- Entidades.
- Value objects.
- Reglas.
- Servicios de dominio.
- Errores de negocio.
- Interfaces puramente conceptuales.

No conoce:

- Android.
- Room.
- Compose.
- Retrofit.
- WorkManager.
- Play Billing.

### Application

Contiene:

- Casos de uso.
- Comandos.
- Consultas.
- Coordinación.
- Validación de flujo.
- Orquestación de transacciones.

### Ports

Interfaces que el dominio o la aplicación necesitan.

Ejemplos:

```kotlin
interface LanguageRepository
interface ConceptRepository
interface CardRepository
interface ReviewRepository
interface ImportRepository
interface AttachmentStorage
interface ReviewScheduler
interface SubscriptionGateway
interface NotificationScheduler
interface SyncGateway
```

### Adapters

Implementaciones concretas:

```text
RoomLanguageRepository
RoomConceptRepository
LocalAttachmentStorage
JsonImportAdapter
WorkManagerNotificationScheduler
PlayBillingSubscriptionGateway
RemoteSyncAdapter
```

### Infrastructure

Configuración y detalles técnicos:

- Room.
- Migraciones.
- Serialización.
- Storage Access Framework.
- WorkManager.
- DataStore.
- Play Billing.
- Red.
- Logs.

### Presentation

- Compose.
- ViewModels.
- UI state.
- Navegación.
- Mappers de presentación.
- Design system.

---

# 21. Estructura de módulos

## 21.1. Propuesta escalable

```text
app/

core/
  domain/
  common/
  designsystem/
  database/
  files/
  preferences/
  testing/

feature/
  onboarding/
  today/
  languages/
  notebook/
  concepts/
  review/
  library/
  statistics/
  importexport/
  settings/
  subscription/

sync/
  api/
  implementation/
```

## 21.2. Alternativa simplificada para MVP

```text
app/
  presentation/
  application/
  domain/
  infrastructure/
```

Se recomienda comenzar con módulos graduales, evitando fragmentar demasiado el MVP.

---

# 22. Puertos principales

```kotlin
interface LanguageRepository {
    fun observeAll(): Flow<List<Language>>
    fun observeById(id: LanguageId): Flow<Language?>
    suspend fun save(language: Language)
    suspend fun archive(id: LanguageId)
    suspend fun delete(id: LanguageId)
}
```

```kotlin
interface ConceptRepository {
    fun search(query: ConceptQuery): Flow<List<Concept>>
    fun observeById(id: ConceptId): Flow<Concept?>
    suspend fun save(concept: Concept)
    suspend fun archive(id: ConceptId)
    suspend fun delete(id: ConceptId)
}
```

```kotlin
interface ReviewRepository {
    fun observeDueCards(query: DueCardQuery): Flow<List<DueCard>>
    suspend fun getState(cardId: CardId): ReviewState
    suspend fun saveState(state: ReviewState)
    suspend fun appendLog(log: ReviewLog)
}
```

```kotlin
interface ImportFileReader {
    suspend fun read(uri: String): ImportDocument
}
```

```kotlin
interface ExportFileWriter {
    suspend fun write(
        document: ExportDocument,
        destinationUri: String
    )
}
```

---

# 23. Casos de uso principales

## Language

```text
CreateLanguage
UpdateLanguage
ArchiveLanguage
DeleteLanguage
DuplicateLanguageStructure
GetLanguages
```

## Notebook

```text
CreateSection
UpdateSection
ReorderSections
HideSection
DeleteSection
```

## Concept

```text
CreateConcept
UpdateConcept
ArchiveConcept
DeleteConcept
SearchConcepts
RelateConcepts
AddExample
AddAttachment
```

## Card

```text
GenerateCardsForConcept
CreateCard
UpdateCard
SuspendCard
ResetCardProgress
```

## Review

```text
GetDueCards
StartStudySession
SubmitReview
PauseStudySession
ResumeStudySession
CompleteStudySession
```

## ImportExport

```text
ValidateImport
PreviewImport
ResolveImportConflicts
ExecuteImport
ExportLanguage
ExportCollection
CreateBackup
RestoreBackup
```

## Statistics

```text
GetProgressSummary
GetLanguageStatistics
GetWeakConcepts
GetStudyHistory
```

## Subscription

```text
GetCurrentPlan
CheckEntitlement
CanCreateLanguage
StartSubscription
RestorePurchases
```

---

# 24. Modelo de datos Room

## 24.1. Tablas principales

```text
languages
sections
concepts
examples
tags
concept_tags
attachments
concept_relations
cards
review_states
review_logs
study_sessions
study_session_cards
collections
collection_concepts
import_batches
import_items
settings
```

## 24.2. Relaciones

```text
Language 1 ─── N Section
Language 1 ─── N Concept
Section 1 ─── N Concept
Concept 1 ─── N Example
Concept 1 ─── N Attachment
Concept 1 ─── N Card
Concept N ─── N Tag
Concept N ─── N ConceptRelation
Card 1 ─── 1 ReviewState
Card 1 ─── N ReviewLog
StudySession 1 ─── N ReviewLog
Collection N ─── N Concept
ImportBatch 1 ─── N ImportItem
```

## 24.3. Índices

Índices recomendados:

```text
languages(status)
sections(language_id, position)
concepts(language_id, section_id)
concepts(type)
concepts(status)
concepts(external_id)
cards(concept_id)
review_states(due_at)
review_states(status)
review_logs(card_id, reviewed_at)
tags(name)
concept_tags(concept_id, tag_id)
```

## 24.4. Búsqueda

Para búsqueda local:

- Consultas `LIKE` en el primer prototipo.
- Room FTS para título, significado, explicación, ejemplos y notas en el MVP estable.

---

# 25. Esquema Room sugerido

## `languages`

```text
id TEXT PRIMARY KEY
name TEXT NOT NULL
code TEXT
base_language_code TEXT
level TEXT
color_token TEXT NOT NULL
icon TEXT
status TEXT NOT NULL
created_at INTEGER NOT NULL
updated_at INTEGER NOT NULL
```

## `sections`

```text
id TEXT PRIMARY KEY
language_id TEXT NOT NULL
parent_section_id TEXT
name TEXT NOT NULL
type TEXT NOT NULL
icon TEXT
color_token TEXT
position INTEGER NOT NULL
practice_enabled INTEGER NOT NULL
visibility TEXT NOT NULL
created_at INTEGER NOT NULL
updated_at INTEGER NOT NULL
```

## `concepts`

```text
id TEXT PRIMARY KEY
external_id TEXT
language_id TEXT NOT NULL
section_id TEXT
type TEXT NOT NULL
title TEXT NOT NULL
meaning TEXT
explanation TEXT
notes TEXT
source TEXT
level TEXT
status TEXT NOT NULL
attributes_json TEXT NOT NULL
created_at INTEGER NOT NULL
updated_at INTEGER NOT NULL
```

## `cards`

```text
id TEXT PRIMARY KEY
concept_id TEXT NOT NULL
type TEXT NOT NULL
front TEXT NOT NULL
back TEXT NOT NULL
hint TEXT
explanation TEXT
direction TEXT
enabled INTEGER NOT NULL
template_id TEXT
created_at INTEGER NOT NULL
updated_at INTEGER NOT NULL
```

## `review_states`

```text
card_id TEXT PRIMARY KEY
due_at INTEGER NOT NULL
last_reviewed_at INTEGER
status TEXT NOT NULL
stability REAL NOT NULL
difficulty REAL NOT NULL
repetitions INTEGER NOT NULL
lapses INTEGER NOT NULL
last_interval_days INTEGER NOT NULL
next_interval_days INTEGER NOT NULL
```

## `review_logs`

```text
id TEXT PRIMARY KEY
card_id TEXT NOT NULL
session_id TEXT
rating TEXT NOT NULL
reviewed_at INTEGER NOT NULL
response_time_ms INTEGER
was_correct INTEGER
previous_interval_days INTEGER NOT NULL
next_interval_days INTEGER NOT NULL
previous_stability REAL
next_stability REAL
previous_difficulty REAL
next_difficulty REAL
```

---

# 26. Formato de importación

## 26.1. Extensión

```text
.langbook.json
```

## 26.2. Ejemplo

```json
{
  "format": "sermomemo-langbook",
  "version": 1,
  "language": {
    "externalId": "fr",
    "name": "Français",
    "code": "fr",
    "baseLanguageCode": "es"
  },
  "sections": [
    {
      "externalId": "vocabulary",
      "name": "Vocabulario",
      "type": "VOCABULARY"
    }
  ],
  "concepts": [
    {
      "externalId": "fr-vocab-pomme",
      "sectionExternalId": "vocabulary",
      "type": "VOCABULARY",
      "title": "pomme",
      "meaning": "manzana",
      "level": "A1",
      "attributes": {
        "gender": "FEMININE",
        "plural": "pommes",
        "pronunciation": "/pɔm/"
      },
      "examples": [
        {
          "target": "Je mange une pomme.",
          "translation": "Como una manzana."
        }
      ],
      "tags": [
        "comida",
        "sustantivos"
      ],
      "cards": [
        {
          "type": "RECOGNITION"
        },
        {
          "type": "PRODUCTION"
        },
        {
          "type": "CLOZE",
          "front": "Je mange une {{c1::pomme}}."
        }
      ]
    }
  ]
}
```

## 26.3. Flujo

```text
Seleccionar archivo
→ leer
→ validar
→ previsualizar
→ resolver conflictos
→ importar
→ mostrar resultado
```

---

# 27. Navegación principal

La barra inferior tendrá cuatro destinos:

```text
Hoy
Idiomas
Biblioteca
Progreso
```

Ajustes se abre desde el menú superior.

```text
App
├── Onboarding
├── Hoy
│   ├── Configurar sesión
│   ├── Estudio
│   └── Resumen
├── Idiomas
│   ├── Crear idioma
│   ├── Detalle de idioma
│   ├── Detalle de sección
│   └── Concepto
├── Biblioteca
│   ├── Búsqueda
│   ├── Filtros
│   └── Colecciones
├── Progreso
│   ├── Resumen
│   ├── Por idioma
│   └── Historial
└── Ajustes
```

---

# 28. Inventario de screens

## Onboarding

### S01 — Splash

- Inicialización.
- Logo.
- Carga breve.
- Recuperación en caso de error.

### S02 — Bienvenida

- Propuesta de valor.
- Beneficios.
- Empezar.
- Importar biblioteca.

### S03 — Crear primer idioma

- Nombre.
- Código.
- Idioma base.
- Nivel.
- Color.
- Plantilla.

### S04 — Preferencias iniciales

- Objetivo.
- Duración.
- Tema.
- Recordatorios.

---

## Hoy

### S10 — Hoy

Debe responder:

> ¿Qué debo practicar ahora?

Incluye:

- Pendientes.
- Tiempo estimado.
- Objetivo diario.
- Idiomas activos.
- Conceptos débiles.
- Captura rápida.
- Actividad reciente.

### S11 — Configurar sesión

- Idiomas.
- Duración.
- Cantidad.
- Nuevos.
- Atrasados.
- Tipos de ejercicio.
- Modo.

### S12 — Estudio

- Progreso.
- Pregunta.
- Respuesta.
- Explicación.
- Rating.
- Suspender.
- Editar.

### S13 — Pausa

- Continuar.
- Finalizar.
- Guardar.
- Salir.

### S14 — Resumen

- Tarjetas.
- Tiempo.
- Resultados.
- Errores.
- Próximos pasos.

---

## Idiomas

### S20 — Lista de idiomas

- Activos.
- Archivados.
- Progreso.
- Pendientes.
- Menú contextual.

### S21 — Crear idioma

- Datos.
- Secciones.
- Plantillas.
- Importación.

### S22 — Detalle del idioma

- Dashboard.
- Secciones.
- Pendientes.
- Conceptos débiles.
- Etiquetas.
- Estadísticas.

### S23 — Lista de secciones

- Nombre.
- Conceptos.
- Dominio.
- Pendientes.

### S24 — Configurar secciones

- Reordenar.
- Renombrar.
- Ocultar.
- Subdividir.
- Definir práctica.

### S25 — Detalle de sección

- Lista.
- Filtros.
- Orden.
- Selección múltiple.
- Iniciar repaso.

---

## Conceptos

### S30 — Captura rápida

- Idioma.
- Tipo.
- Título.
- Significado.
- Guardar.

### S31 — Selector de tipo

- Vocabulario.
- Expresión.
- Gramática.
- Pronunciación.
- Error.
- Nota.

### S32 — Vocabulario

- Palabra.
- Traducción.
- Definición.
- Género.
- Pronunciación.
- Ejemplos.
- Etiquetas.
- Tarjetas.

### S33 — Expresión

- Expresión.
- Significado.
- Literal.
- Registro.
- Contexto.
- Ejemplos.

### S34 — Gramática

- Título.
- Explicación.
- Fórmula.
- Ejemplos.
- Excepciones.
- Errores.

### S35 — Pronunciación

- Sonido.
- IPA.
- Audio.
- Pares mínimos.
- Posición articulatoria.

### S36 — Error

- Incorrecto.
- Corrección.
- Explicación.
- Fuente.
- Frecuencia.
- Ejercicio.

### S37 — Nota

- Título.
- Texto.
- Adjuntos.
- Relaciones.
- Etiquetas.

### S38 — Detalle

- Datos.
- Ejemplos.
- Estado.
- Dominio.
- Próximo repaso.
- Historial.
- Relaciones.
- Adjuntos.

### S39 — Tarjetas del concepto

- Tipos.
- Próximo repaso.
- Dificultad.
- Estado.
- Acciones.

### S40 — Editor de tarjeta

- Front.
- Back.
- Pista.
- Explicación.
- Dirección.
- Tipo.

### S41 — Relaciones

- Sinónimos.
- Antónimos.
- Confusiones.
- Derivaciones.
- Excepciones.

---

## Biblioteca

### S50 — Biblioteca global

- Todos los conceptos.
- Filtros.
- Orden.
- Selección múltiple.

### S51 — Búsqueda

- Título.
- Traducción.
- Definición.
- Ejemplo.
- Etiquetas.

### S52 — Filtros avanzados

- Idioma.
- Tipo.
- Nivel.
- Estado.
- Dominio.
- Fecha.
- Adjuntos.

### S53 — Colecciones

- Crear.
- Compartir.
- Exportar.
- Repasar.

---

## Importación y exportación

### S60 — Centro

- Importar.
- Exportar.
- Copias.
- Historial.
- Documentación.

### S61 — Selección de archivo

- Formatos.
- Privacidad.
- Ejemplos.

### S62 — Validación

- Lectura.
- Versión.
- Estructura.
- Conflictos.

### S63 — Vista previa

- Idioma.
- Secciones.
- Conceptos.
- Tarjetas.
- Adjuntos.
- Errores.

### S64 — Conflictos

- Crear.
- Actualizar.
- Omitir.
- Duplicar.

### S65 — Resultado

- Creados.
- Actualizados.
- Fallidos.
- Advertencias.

### S66 — Configurar exportación

- Contenido.
- Historial.
- Adjuntos.
- Nombre.
- Destino.

### S67 — Historial

- Fecha.
- Archivo.
- Resultado.
- Informe.

---

## Progreso

### S70 — Resumen

- Retención.
- Tiempo.
- Revisados.
- Débiles.
- Próxima carga.

### S71 — Por idioma

- Retención.
- Secciones.
- Tiempo.
- Errores.
- Pendientes.

### S72 — Memoria

- Estabilidad.
- Dificultad.
- Lapsos.
- Intervalo.

### S73 — Historial de estudio

- Sesiones.
- Fecha.
- Duración.
- Resultado.

### S74 — Conceptos débiles

- Lapsos.
- Errores.
- Sin ejemplos.
- Rendimiento desigual.

### S75 — Calendario

- Días.
- Duración.
- Idiomas.
- Sesiones.

---

## Plantillas

### S80 — Plantillas de concepto

- Sustantivo.
- Verbo.
- Expresión.
- Gramática.
- Error.

### S81 — Editor de plantilla

- Campos.
- Orden.
- Obligatorios.
- Tarjetas automáticas.

### S82 — Plantillas de tarjetas

- Directa.
- Inversa.
- Cloze.
- Audio.
- Definición.

---

## Ajustes

### S90 — General

### S91 — Apariencia

### S92 — Estudio

### S93 — Notificaciones

### S94 — Datos y copias

### S95 — Accesibilidad

### S96 — Acerca de

---

## Sistema

### S100 — Estado vacío

### S101 — Error recuperable

### S102 — Recuperación de datos

### S103 — Confirmación destructiva

### S104 — Snackbar y deshacer

---

# 29. Dirección visual

## 29.1. Sensación

La interfaz debe transmitir:

- Calma.
- Concentración.
- Curiosidad.
- Crecimiento.
- Propiedad.
- Profundidad.
- Claridad.

## 29.2. Evitar

- Infantilización.
- Gamificación agresiva.
- Saturación.
- Colores estridentes.
- Castigo por perder rachas.
- Exceso de gráficos.
- Apariencia corporativa.
- Dependencia del color.

## 29.3. Estilo

```text
Cuaderno limpio
+ herramienta de estudio
+ biblioteca personal
```

---

# 30. Paleta Light

| Token | Hex | Uso |
|---|---:|---|
| `primary` | `#C96F1A` | Acción principal |
| `onPrimary` | `#FFFFFF` | Texto sobre primary |
| `primaryContainer` | `#FFE2C3` | Fondos destacados |
| `onPrimaryContainer` | `#4A2808` | Texto sobre container |
| `secondary` | `#5364A8` | Acciones secundarias |
| `onSecondary` | `#FFFFFF` | Texto sobre secondary |
| `secondaryContainer` | `#E0E5FF` | Fondos secundarios |
| `onSecondaryContainer` | `#17204C` | Texto secundario |
| `tertiary` | `#3F7D6A` | Consolidación y relaciones |
| `onTertiary` | `#FFFFFF` | Texto sobre tertiary |
| `tertiaryContainer` | `#C8EDDF` | Fondo tertiary |
| `onTertiaryContainer` | `#092F26` | Texto tertiary |
| `error` | `#B94A48` | Error y eliminación |
| `onError` | `#FFFFFF` | Texto sobre error |
| `errorContainer` | `#FFDAD8` | Fondo error |
| `onErrorContainer` | `#410006` | Texto error |
| `background` | `#FCF8F4` | Fondo general |
| `onBackground` | `#211A16` | Texto general |
| `surface` | `#FFFBF8` | Superficies |
| `surfaceVariant` | `#F2E8DF` | Superficies secundarias |
| `onSurface` | `#211A16` | Texto superficie |
| `onSurfaceVariant` | `#51453D` | Texto secundario |
| `outline` | `#85756A` | Bordes |
| `outlineVariant` | `#D8C8BD` | Divisores |
| `inverseSurface` | `#372F2A` | Snackbar |
| `inverseOnSurface` | `#FCEFE8` | Texto inverso |
| `scrim` | `#000000` | Overlay |

---

# 31. Paleta Dark

| Token | Hex | Uso |
|---|---:|---|
| `primary` | `#FFB86B` | Acción principal |
| `onPrimary` | `#512900` | Texto sobre primary |
| `primaryContainer` | `#743B00` | Fondos destacados |
| `onPrimaryContainer` | `#FFE2C3` | Texto sobre container |
| `secondary` | `#BAC3FF` | Acciones secundarias |
| `onSecondary` | `#24306B` | Texto sobre secondary |
| `secondaryContainer` | `#3A477F` | Fondos secundarios |
| `onSecondaryContainer` | `#E0E5FF` | Texto secundario |
| `tertiary` | `#AAD1C2` | Consolidación y relaciones |
| `onTertiary` | `#12382E` | Texto tertiary |
| `tertiaryContainer` | `#2A5045` | Fondo tertiary |
| `onTertiaryContainer` | `#C8EDDF` | Texto tertiary |
| `error` | `#FFB4AB` | Error |
| `onError` | `#690005` | Texto error |
| `errorContainer` | `#93000A` | Fondo error |
| `onErrorContainer` | `#FFDAD6` | Texto error |
| `background` | `#19120E` | Fondo general |
| `onBackground` | `#F0DFD6` | Texto general |
| `surface` | `#211914` | Superficies |
| `surfaceVariant` | `#51453D` | Superficies secundarias |
| `onSurface` | `#F0DFD6` | Texto superficie |
| `onSurfaceVariant` | `#D8C8BD` | Texto secundario |
| `outline` | `#A08E82` | Bordes |
| `outlineVariant` | `#51453D` | Divisores |
| `inverseSurface` | `#F0DFD6` | Superficie inversa |
| `inverseOnSurface` | `#372F2A` | Texto inverso |
| `scrim` | `#000000` | Overlay |

---

# 32. Colores semánticos

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

# 33. Colores por idioma

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

El color del idioma:

- No sustituye el tema.
- Se utiliza en pequeños acentos.
- Debe tener variante dark.
- No debe reducir contraste.

---

# 34. Tokens de tipografía

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

| Token | Tamaño | Línea | Peso |
|---|---:|---:|---:|
| `displayLarge` | 40sp | 48sp | 600 |
| `displayMedium` | 34sp | 42sp | 600 |
| `headlineLarge` | 30sp | 38sp | 600 |
| `headlineMedium` | 26sp | 34sp | 600 |
| `titleLarge` | 22sp | 28sp | 600 |
| `titleMedium` | 18sp | 24sp | 600 |
| `bodyLarge` | 16sp | 24sp | 400 |
| `bodyMedium` | 14sp | 20sp | 400 |
| `bodySmall` | 12sp | 16sp | 400 |
| `labelLarge` | 14sp | 20sp | 600 |
| `labelMedium` | 12sp | 16sp | 600 |
| `labelSmall` | 11sp | 14sp | 600 |

---

# 35. Tokens de espaciado

Base de 4dp:

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

---

# 36. Tokens de radio

| Token | Valor | Uso |
|---|---:|---|
| `radius.none` | 0dp | Divisores |
| `radius.xs` | 4dp | Indicadores |
| `radius.sm` | 8dp | Inputs compactos |
| `radius.md` | 12dp | Tarjetas |
| `radius.lg` | 16dp | Paneles |
| `radius.xl` | 24dp | Tarjetas destacadas |
| `radius.full` | 999dp | Chips |

## Regla

No utilizar `radius.full` en todos los componentes. Debe reservarse para chips, estados y avatares.

---

# 37. Tokens de elevación

| Token | Valor |
|---|---:|
| `none` | 0dp |
| `low` | 1dp |
| `medium` | 3dp |
| `high` | 6dp |
| `modal` | 12dp |

Priorizar bordes y diferencias de superficie.

---

# 38. Tokens táctiles

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

# 39. Tokens de movimiento

| Token | Duración |
|---|---:|
| `motion.fast` | 100ms |
| `motion.short` | 180ms |
| `motion.medium` | 280ms |
| `motion.long` | 420ms |

Reglas:

- Explicar cambios.
- Evitar animación permanente.
- Respetar reducción de movimiento.
- No utilizar confeti frecuente.

---

# 40. Design system

## 40.1. Nombre

```text
SermoMemo Design System
```

Abreviatura:

```text
SMDS
```

## 40.2. Estructura

```text
foundation/
  colors
  typography
  spacing
  shapes
  elevation
  motion
  iconography

components/
  buttons
  inputs
  cards
  chips
  navigation
  feedback
  dialogs
  study

patterns/
  forms
  empty-states
  concept-detail
  study-session
  import-flow
  dashboards
```

## 40.3. Componentes

```text
SermoButton
SermoOutlinedButton
SermoTextField
LanguageCard
SectionCard
ConceptCard
MasteryBadge
ReviewRatingBar
StudyProgress
FilterChipGroup
EmptyState
ErrorState
SectionHeader
ImportSummary
StatisticsCard
```

---

# 41. Componentes principales

## Botón principal

- Fondo primary.
- Texto onPrimary.
- Altura mínima 48dp.
- Loading.
- Disabled.
- Icono opcional.

## Tarjeta de idioma

- Nombre.
- Color.
- Pendientes.
- Retención.
- Última actividad.

## Tarjeta de concepto

- Tipo.
- Título.
- Significado.
- Etiquetas.
- Estado.
- Próximo repaso.

## Mastery badge

Ejemplo:

```text
Fuerte · 82%
```

Incluye texto, icono y color.

## Review rating bar

Botones:

```text
Otra vez
Difícil
Bien
Fácil
```

## Empty state

- Ilustración.
- Título.
- Descripción.
- Acción.

---

# 42. Accesibilidad

- WCAG AA.
- Texto normal 4.5:1.
- Texto grande 3:1.
- Targets de 48dp.
- Texto escalable.
- Audio con alternativa textual.
- Estados con texto e icono.
- Reducción de movimiento.
- Compatibilidad con lector de pantalla.
- No depender de rojo y verde.

---

# 43. Responsive

## Móvil compacto

- Bottom navigation.
- Una columna.
- Bottom sheets.

## Móvil grande

- Una columna amplia.
- Formularios agrupados.

## Tablet

- Navigation rail.
- Maestro-detalle.
- Dos columnas.

## Plegables

- Evitar bisagra.
- Panel doble.

---

# 44. MVP

## 44.1. Objetivo

Validar el ciclo esencial:

```text
Crear idioma
→ añadir concepto
→ generar tarjeta
→ estudiar
→ registrar progreso
→ exportar
```

## 44.2. Incluido

### Producto

- Onboarding.
- Crear idiomas.
- Límite por plan.
- Secciones predeterminadas.
- Personalización básica.
- Vocabulario.
- Expresiones.
- Gramática.
- Errores.
- Notas.
- Ejemplos.
- Etiquetas.
- Tarjetas de reconocimiento.
- Producción.
- Cloze.
- Cola diaria.
- Ratings.
- Historial.
- Estadísticas básicas.
- Importar JSON.
- Exportar JSON.
- Copia manual.
- Light y dark mode.
- Accesibilidad base.
- Funcionamiento offline.

### Tecnología

- Kotlin.
- Jetpack Compose.
- Room.
- Flow.
- Coroutines.
- DataStore.
- Kotlin Serialization.
- WorkManager.
- Storage Access Framework.

## 44.3. Excluido

- IA generativa.
- Sincronización.
- Colaboración.
- Reconocimiento de voz.
- Backend.
- Web.
- Importación Anki.
- Tienda de colecciones.
- Social.
- Leaderboards.

---

# 45. Roadmap

## Fase 1 — MVP

- Flujo local completo.
- Importación y exportación.
- Repetición espaciada sencilla.
- Plan gratuito y plan individual.

## Fase 2 — Aprendizaje avanzado

- FSRS.
- Audio.
- Dictado.
- Pronunciación.
- Plantillas.
- Relaciones.
- Estadísticas avanzadas.
- Recordatorios.

## Fase 3 — Cuenta y sincronización

- Autenticación opcional.
- Sincronización cifrada.
- Copias automáticas.
- Multi-dispositivo.
- Resolución de conflictos.

## Fase 4 — Grupos

- Profesores.
- Familias.
- Equipos.
- Bibliotecas compartidas.
- Asignación de colecciones.
- Progreso agregado.
- Roles.

## Fase 5 — IA asistida

- Generar ejemplos.
- Detectar errores.
- Proponer tarjetas.
- Crear cloze.
- Adaptar nivel.
- Convertir texto importado en conceptos.

La IA nunca debe sustituir la edición del usuario ni modificar datos sin confirmación.

## Fase 6 — Ecosistema

- Aplicación web.
- Aplicación desktop.
- Extensión de navegador.
- Compartir desde otras apps.
- API pública.
- Marketplace de colecciones.

---

# 46. Modelo de monetización

## 46.1. Principios

- La versión gratuita debe ser útil.
- No bloquear la exportación.
- No introducir anuncios durante estudio.
- No vender datos.
- No castigar la cancelación.
- No bloquear conocimiento creado.
- Los límites deben ser comprensibles.

## 46.2. Eje principal

La monetización inicial se basa en:

- Cantidad de idiomas activos.
- Sincronización.
- Copias automáticas.
- Funciones avanzadas.
- Colaboración.
- Uso grupal.

---

# 47. Plan Free

Nombre:

```text
SermoMemo Free
```

Incluye:

- 2 idiomas activos.
- Idiomas archivados ilimitados o con límite razonable.
- Conceptos locales amplios.
- Flashcards.
- Repetición espaciada.
- Estadísticas básicas.
- Importación JSON.
- Exportación completa.
- Copia manual.
- Light y dark mode.

Limitaciones:

- Sin sincronización.
- Sin copias automáticas.
- Sin grupos.
- Sin IA.
- Plantillas limitadas.
- Estadísticas avanzadas no incluidas.

## Motivo del límite

Dos idiomas permiten validar el producto sin impedir su uso real.

---

# 48. Plan Individual

Nombre:

```text
SermoMemo Plus
```

Incluye:

- Hasta 10 idiomas activos.
- Sincronización.
- Copias automáticas.
- Audio.
- Plantillas personalizadas.
- Estadísticas avanzadas.
- Importaciones adicionales.
- Colecciones ilimitadas.
- Personalización avanzada.
- Historial completo.
- Soporte prioritario.

## Variante

```text
SermoMemo Unlimited
```

Incluye:

- Idiomas activos ilimitados.
- Todas las funciones individuales.
- Mayor almacenamiento.
- IA con cuota mensual.

---

# 49. Plan Group

Nombre:

```text
SermoMemo Groups
```

Público:

- Familias.
- Parejas.
- Grupos de estudio.
- Academias pequeñas.
- Profesores.

Incluye:

- Espacio compartido.
- Administrador.
- Miembros.
- Colecciones compartidas.
- Asignaciones.
- Progreso agregado.
- Permisos.
- Comentarios.
- Hasta un número definido de miembros.

## Roles

```text
Owner
Admin
Teacher
Member
Viewer
```

## Funciones

- Crear grupo.
- Invitar.
- Asignar colección.
- Ver progreso.
- Compartir idiomas.
- Duplicar materiales.
- Exportar grupo.
- Separar datos personales de datos compartidos.

---

# 50. Plan Education

Nombre:

```text
SermoMemo Education
```

Público:

- Profesores.
- Academias.
- Escuelas.
- Programas internos.

Incluye:

- Clases.
- Alumnos.
- Material común.
- Asignaciones.
- Panel docente.
- Importación masiva.
- Estadísticas por clase.
- Gestión de licencias.
- Privacidad reforzada.
- Exportación institucional.

---

# 51. Propuesta de límites

| Plan | Idiomas activos | Miembros | Sync | Grupos |
|---|---:|---:|---|---|
| Free | 2 | 1 | No | No |
| Plus | 10 | 1 | Sí | No |
| Unlimited | Ilimitados | 1 | Sí | No |
| Groups | Ilimitados | 5–20 | Sí | Sí |
| Education | Según licencia | 20+ | Sí | Sí |

Estos límites deben poder ajustarse sin migraciones de dominio mediante configuración remota o catálogo de producto.

---

# 52. Compras adicionales

Posibles add-ons:

- Paquetes de almacenamiento.
- Créditos de IA.
- Más miembros.
- Bibliotecas premium.
- Plantillas profesionales.
- Importaciones especializadas.
- Pronunciación avanzada.

No se recomienda cobrar por:

- Exportar datos.
- Consultar contenido propio.
- Acceder a historial ya creado.
- Recuperar datos después de cancelar.

---

# 53. Estrategia de precios

La aplicación puede utilizar:

- Suscripción mensual.
- Suscripción anual con descuento.
- Plan familiar.
- Plan educativo.
- Compra de por vida limitada para funciones offline.

## Alternativa lifetime

Un plan de pago único puede incluir:

- Idiomas ilimitados.
- Funciones offline.
- Sin sincronización perpetua garantizada.
- Sin IA incluida.
- Sin costes de servidor recurrentes.

Esto permite monetizar usuarios que rechazan suscripciones.

---

# 54. Arquitectura futura de sincronización

## 54.1. Principio local-first

Room sigue siendo la fuente local.

```text
UI
→ Room
→ Sync Queue
→ Backend
```

## 54.2. Necesidades

- Identificadores UUID.
- `createdAt`.
- `updatedAt`.
- `deletedAt`.
- Versiones.
- Resolución de conflictos.
- Eventos.
- Sincronización incremental.
- Cifrado.

## 54.3. Conflictos

Estrategias:

- Last write wins para preferencias simples.
- Merge por campo para conceptos.
- Append-only para review logs.
- Confirmación para conflictos complejos.

---

# 55. Backend futuro

Posible stack:

```text
Spring Boot
PostgreSQL
Object Storage
Redis
WebSocket o SSE
OAuth / OpenID Connect
```

Dominios backend:

```text
Identity
Subscription
Sync
Group
SharedLibrary
AI
Notification
Audit
```

---

# 56. Seguridad y privacidad

## MVP

- Datos locales.
- Sin cuenta.
- Archivos internos.
- Storage Access Framework.
- Exportación explícita.

## Futuro

- Cifrado en tránsito.
- Cifrado de datos sensibles.
- Tokens seguros.
- Revocación.
- Gestión de sesiones.
- Eliminación de cuenta.
- Exportación.
- Consentimiento.
- Minimización de datos.

---

# 57. Telemetría

Debe ser opcional y respetuosa.

Eventos útiles:

```text
language_created
concept_created
review_started
review_completed
import_completed
export_completed
subscription_started
```

No recopilar:

- Contenido de conceptos.
- Palabras guardadas.
- Notas.
- Audios.
- Traducciones.
- Archivos personales.

---

# 58. Métricas de producto

## Activación

- Primer idioma creado.
- Primer concepto.
- Primera sesión.
- Primera exportación.

## Retención

- Usuarios activos semanales.
- Sesiones por semana.
- Conceptos repasados.
- Retorno después de siete días.

## Aprendizaje

- Retención.
- Lapsos.
- Conceptos fuertes.
- Sesiones completadas.

## Negocio

- Conversión Free → Plus.
- Conversión Plus → Unlimited.
- Creación de grupos.
- Renovación anual.
- Cancelación.

---

# 59. Riesgos

## Complejidad excesiva

Mitigación:

- MVP limitado.
- Progressive disclosure.
- Tipos principales primero.

## Captura lenta

Mitigación:

- Captura rápida.
- Valores por defecto.
- Plantillas.

## Algoritmo difícil de entender

Mitigación:

- Estados comprensibles.
- Explicaciones.
- Ocultar parámetros técnicos.

## Importaciones corruptas

Mitigación:

- Validación.
- Vista previa.
- Transacción.
- Informe.

## Dependencia de suscripción

Mitigación:

- Modo offline gratuito.
- Exportación.
- Lifetime.

## Saturación de mercado

Mitigación:

- Diferenciar por notebook estructurado.
- Propiedad de datos.
- Personalización.
- Práctica activa.

---

# 60. Criterios de éxito del MVP

- Crear palabra en menos de 20 segundos.
- Iniciar repaso en máximo dos toques desde Hoy.
- Buscar concepto en pocos segundos.
- Importar sin modificar datos antes de confirmar.
- Exportar sin cuenta.
- Funcionar sin conexión.
- No perder historial.
- Light y dark consistentes.
- Navegación comprensible.
- Usuario capaz de explicar el valor principal tras una sesión.

---

# 61. Backlog priorizado

## Must

- Idiomas.
- Secciones.
- Conceptos.
- Tarjetas.
- Repaso.
- Historial.
- Importar.
- Exportar.
- Búsqueda.
- Ajustes.
- Tema.
- Copia.

## Should

- Colecciones.
- Plantillas.
- Audio.
- Estadísticas avanzadas.
- Recordatorios.
- Relaciones.

## Could

- Pronunciación.
- Dictado.
- Compartir.
- Sincronización.
- IA.
- Web.

## Won't en MVP

- Social.
- Marketplace.
- Ranking.
- Chat.
- Cursos completos.
- Vídeo.
- Tutor IA.

---

# 62. Convenciones técnicas

## IDs

Utilizar value objects:

```kotlin
@JvmInline
value class LanguageId(val value: String)
```

Preferir UUID si se prevé sincronización.

## Tiempo

Utilizar `Instant` en dominio e infraestructura.

## Errores

```kotlin
sealed interface DomainError
```

## Resultados

```kotlin
sealed interface UseCaseResult<out T>
```

## Flujos

- `Flow` para observación.
- `suspend` para comandos.
- No exponer entidades Room a presentación.

---

# 63. Testing

## Dominio

- Reglas.
- Scheduler.
- Validación.
- Conflictos.
- Límites de plan.

## Application

- Casos de uso.
- Repositorios fake.
- Escenarios.

## Infrastructure

- Room.
- Migraciones.
- JSON.
- Archivos.

## UI

- Compose tests.
- Accesibilidad.
- Screenshots.
- Estados.

## End-to-end

- Crear idioma.
- Crear concepto.
- Repasar.
- Exportar.
- Importar.
- Restaurar.

---

# 64. Migraciones Room

Cada versión debe incluir:

- Migración explícita.
- Test de migración.
- Copia previa para migraciones críticas.
- Nunca usar destrucción automática en producción.
- Registro de fallos.

---

# 65. Organización del proyecto Compose

```text
presentation/
  navigation/
  theme/
  components/
  screens/
  state/
  mapper/

application/
  usecase/
  command/
  query/

domain/
  language/
  notebook/
  concept/
  card/
  review/
  practice/
  importexport/
  subscription/

infrastructure/
  room/
  datastore/
  files/
  scheduler/
  billing/
```

---

# 66. Propuesta de package naming

```text
com.sermomemo.app
com.sermomemo.domain
com.sermomemo.application
com.sermomemo.infrastructure
com.sermomemo.presentation
```

---

# 67. Tema Compose

```kotlin
@Composable
fun SermoMemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            SermoDarkColorScheme
        } else {
            SermoLightColorScheme
        },
        typography = SermoTypography,
        shapes = SermoShapes,
        content = content
    )
}
```

---

# 68. Recomendación de implementación por iteraciones

## Iteración 1

- Design system base.
- Navegación.
- Room.
- Language.
- Section.

## Iteración 2

- Conceptos.
- Ejemplos.
- Etiquetas.
- Búsqueda.

## Iteración 3

- Tarjetas.
- Scheduler simple.
- Sesión de estudio.
- Historial.

## Iteración 4

- Estadísticas.
- Importación.
- Exportación.
- Copias.

## Iteración 5

- Monetización.
- Límite por idiomas.
- Pulido.
- Accesibilidad.
- Publicación.

---

# 69. Conclusión

SermoMemo debe construirse como una plataforma de conocimiento lingüístico personal, no como una aplicación de tarjetas aisladas.

La fortaleza del producto reside en unir:

```text
Notebook estructurado
+ práctica activa
+ repetición espaciada
+ portabilidad
+ personalización
```

El MVP debe demostrar que el usuario puede capturar conocimiento rápidamente y convertirlo en una práctica útil.

La evolución posterior debe centrarse en:

- Mejorar la memoria.
- Añadir audio y pronunciación.
- Sincronizar.
- Compartir.
- Ofrecer grupos.
- Introducir asistencia inteligente.
- Expandirse a web y escritorio.

La monetización debe ser clara, ética y compatible con la propiedad de los datos. El límite de idiomas activos ofrece un modelo comprensible, mientras que sincronización, colaboración, IA y planes grupales permiten construir ingresos recurrentes sin degradar la experiencia principal.

---

# 70. Definición resumida

> SermoMemo es un notebook activo para idiomas: captura lo que aprendes, organízalo a tu manera, practícalo y recuérdalo.
