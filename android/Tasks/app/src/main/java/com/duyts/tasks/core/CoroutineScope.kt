package com.duyts.tasks.core

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class BackgroundScope
