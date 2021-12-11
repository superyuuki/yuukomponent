package com.superyuuki.yuukomponent.api.trait;

/**
 * The source of every behavior in existence.
 */
public interface TraitFactory {

    Trait createOfType(String componentIdentifier);

}
