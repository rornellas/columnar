package br.com.fiap.cassandra.columnar

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
class Pessoa(@field:PrimaryKey val id: String, val nome: String, val idade: Int) {

    override fun toString(): String {
        return String.format("{ @type = %1\$s, id = %2\$s, nome = %3\$s, idade = %4\$d }",
                javaClass.name, id, nome, idade)
    }

}