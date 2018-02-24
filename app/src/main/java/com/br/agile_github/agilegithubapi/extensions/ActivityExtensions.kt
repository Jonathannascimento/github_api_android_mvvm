package com.br.agile_github.agilegithubapi.extensions

import android.support.v7.app.AppCompatActivity

/**
 * A group of *extensions*.
 *
 * Add useful extensions to all application activities, similar to swift
 *
 */
fun AppCompatActivity.enableToolbarBackButton() {
    delegate.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
