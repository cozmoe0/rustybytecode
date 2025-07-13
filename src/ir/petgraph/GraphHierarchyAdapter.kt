/**
 * Type hierarchy graph implementations.
 */
import java.util.*

// Assuming these interfaces exist in a graph library (similar to petgraph)
interface GraphBase {
    // EdgeId and NodeId would be defined by implementing classes
}

interface GraphRef : GraphBase

interface IntoNeighbors : GraphBase {
    fun neighbors(nodeId: Any): Iterator<Any>
}

interface IntoNeighborsDirected : IntoNeighbors {
    fun neighborsDirected(nodeId: Any, direction: Direction): Iterator<Any>
}

interface Visitable : GraphBase {
    fun visitMap(): MutableSet<Any>
    fun resetMap(map: MutableSet<Any>)
}

enum class Direction {
    Incoming, Outgoing
}

// Type alias for visited map
typealias Visited = MutableSet<ClassRef>

// Extension functions for ClassHierarchy to implement graph interfaces
class ClassHierarchyGraphAdapter(private val hierarchy: ClassHierarchy) : 
    GraphRef, IntoNeighbors, Visitable {
    
    override fun neighbors(nodeId: Any): Iterator<ClassRef> {
        val classRef = nodeId as ClassRef
        return hierarchy.inheritance[classRef]
            ?.flatten()
            ?.toSet()
            ?.iterator() ?: emptySet<ClassRef>().iterator()
    }
    
    override fun visitMap(): MutableSet<Any> {
        return HashSet()
    }
    
    override fun resetMap(map: MutableSet<Any>) {
        map.clear()
    }
}

// Extension functions for InterfaceImplHierarchy to implement graph interfaces
class InterfaceImplHierarchyGraphAdapter(private val hierarchy: InterfaceImplHierarchy) : 
    GraphRef, IntoNeighbors, IntoNeighborsDirected, Visitable {
    
    override fun neighbors(nodeId: Any): Iterator<ClassRef> {
        return neighborsDirected(nodeId, Direction.Outgoing)
    }
    
    override fun neighborsDirected(nodeId: Any, direction: Direction): Iterator<ClassRef> {
        val classRef = nodeId as ClassRef
        return if (direction == Direction.Outgoing) {
            hierarchy.implementations[classRef]
                ?.flatten()
                ?.toSet()
                ?.iterator() ?: emptySet<ClassRef>().iterator()
        } else {
            hierarchy.implementors[classRef]
                ?.flatten()
                ?.toSet()
                ?.iterator() ?: emptySet<ClassRef>().iterator()
        }
    }
    
    override fun visitMap(): MutableSet<Any> {
        return HashSet()
    }
    
    override fun resetMap(map: MutableSet<Any>) {
        map.clear()
    }
}

// Assuming these classes exist in your IR module
// class ClassHierarchy {
//     val inheritance: Map<ClassRef, Collection<ClassRef>> = mapOf()
// }

// class InterfaceImplHierarchy {
//     val implementations: Map<ClassRef, Collection<ClassRef>> = mapOf()
//     val implementors: Map<ClassRef, Collection<ClassRef>> = mapOf()
// }

// class ClassRef
