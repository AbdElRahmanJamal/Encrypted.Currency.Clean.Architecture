package com.app.domain

interface EntityMapper<ENTITY, DOMAIN> {

    fun convert(first: ENTITY): DOMAIN
}