package br.com.fiap.cassandra.columnar

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.query.Criteria
import org.springframework.data.cassandra.core.query.Query
import java.util.*

@SpringBootApplication
class ColumnarApplication

fun main(args: Array<String>) {
	val cluster: Cluster = Cluster.builder().addContactPoints("localhost").withoutMetrics().build()
	val session: Session = cluster.connect("test")
	val template: CassandraOperations = CassandraTemplate(session)
	val joao = template.insert(Pessoa(UUID.randomUUID().toString(), "Joao Silva", 42))
	println("""Quantidade de itens na table: ${template.count(Pessoa::class.java)}""".trimIndent())

	println(template.exists(Query.query(Criteria.where("nome").`is`("Joao Silva")).withAllowFiltering(), Pessoa::class.java))

	System.out.println(template.selectOne(Query.query(Criteria.where("id").`is`(joao.id)), Pessoa::class.java))
	session.close()
	cluster.close()
}
