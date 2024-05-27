package com.po.bookDB.ui.network

import javax.inject.Qualifier

object QualifiedAnnotation {

    //Coroutines
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Default

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Io

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Main

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ViewScope

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class RepoScope

    //retrofit

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class BookRetrofit

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class UserRetrofit

    //Datastore
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AuthPref

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class UserPref

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class UserProto

}