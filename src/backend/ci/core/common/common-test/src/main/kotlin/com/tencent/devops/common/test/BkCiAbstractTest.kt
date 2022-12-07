package com.tencent.devops.common.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.tencent.devops.common.client.Client
import com.tencent.devops.common.redis.RedisOperation
import io.mockk.MockKMatcherScope
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.spyk
import org.apache.commons.lang3.reflect.MethodUtils
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Result
import org.jooq.SQLDialect
import org.jooq.Table
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.Mock
import org.jooq.tools.jdbc.MockConnection
import org.junit.jupiter.api.BeforeAll
import org.springframework.data.redis.core.RedisCallback
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass

open class BkCiAbstractTest {
    val dslContext: DSLContext = DSL.using(MockConnection(Mock.of(0)), SQLDialect.MYSQL)
    val objectMapper: ObjectMapper = spyk()


    /**
     * Mock JooQ 的返回
     * records 不传值 , Result为空
     */
    fun <R : Record> DSLContext.mockResult(t: Table<R>, vararg records: R): Result<R> {
        val result = newResult(t)
        records.forEach { result.add(it) }
        return result
    }

    /**
     * dslContext的any()
     */
    fun MockKMatcherScope.anyDslContext(): DSLContext = any() as DSLContext

    /**
     * 调用私有函数
     */
    inline fun <reified R> Any.invokePrivate(methodName: String, vararg args: Any): R? {
        try {
            val invokeResult = MethodUtils.invokeMethod(this, true, methodName, *args) ?: return null
            if (invokeResult is R) {
                return invokeResult
            } else {
                throw IllegalArgumentException("Result type is illegal")
            }
        } catch (e: Throwable) {
            throw if (e is InvocationTargetException) e.targetException else e
        }
    }

    companion object {
        val redisOperation: RedisOperation = mockk(relaxed = true)
        val client: Client = mockk(relaxed = true)
        private val mockResourceMap = mutableMapOf<KClass<*>, Any>()

        @JvmStatic
        @BeforeAll
        fun mockGet() {
            every { client.get(any() as KClass<*>) } answers {
                val clz = args[0] as KClass<*>
                if (mockResourceMap.contains(clz)) {
                    return@answers mockGet(clz)
                }
                val mockResource = mockkClass(clz)
                mockResourceMap[clz] = mockResource
                return@answers mockResource
            }
        }

        @JvmStatic
        fun <T : Any> mockGet(clz: KClass<T>): T {
            return mockResourceMap[clz] as T
        }


        @JvmStatic
        @BeforeAll
        @SuppressWarnings("TooGenericExceptionThrown")
        fun mockRedisOperation() {
            every { redisOperation.execute(any<RedisCallback<*>>()) } answers {
                val argStr = args[0]!!::class.toString()
                if (argStr.contains("RedisLock\$set")) {
                    return@answers "OK"
                } else if (argStr.contains("RedisLock\$unlock")) {
                    return@answers true
                } else {
                    throw Exception("redisOperation.execute must mock by self")
                }
            }
        }
    }
}
