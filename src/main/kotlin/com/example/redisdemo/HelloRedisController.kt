package com.example.redisdemo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/redis")
class HelloRedisController {
    val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate

    @GetMapping("/put")
    fun put(@RequestParam("key") key: String,
            @RequestParam("value") value: String): Result<String> {
        return try {
            stringRedisTemplate.opsForValue().set(key, value)
            Result(data = key, success = true, msg = null)
        } catch (e: Exception) {
            log.error("testRedis put:", e)
            Result(data = key, success = false, msg = e.message)
        }
    }

    @GetMapping("/get")
    fun get(@RequestParam("key") key: String): Result<Any> {
        return try {
            val v = stringRedisTemplate.opsForValue().get(key)
            Result(data = v, success = true, msg = null)
        } catch (e: Exception) {
            log.error("testRedis put:", e)
            Result(data = null, success = false, msg = e.message)
        }
    }


}
