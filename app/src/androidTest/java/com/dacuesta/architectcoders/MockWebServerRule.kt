package com.dacuesta.architectcoders

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.concurrent.thread

class MockWebServerRule : TestRule {
    val server = MockWebServer()

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                server.start()
                replaceBaseUrl()

                base.evaluate()

                server.shutdown()
            }
        }

    private fun replaceBaseUrl() {
        val testModule = module {
            single(named("baseUrl"), override = true) { askMockServerUrlOnAnotherThread() }
        }
        loadKoinModules(testModule)
    }

    private fun askMockServerUrlOnAnotherThread(): String {
        var url = ""
        val t = thread {
            url = server.url("/").toString()
        }
        t.join()
        return url
    }

}