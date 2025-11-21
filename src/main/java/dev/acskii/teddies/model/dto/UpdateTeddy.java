package dev.acskii.teddies.model.dto;

import dev.acskii.teddies.model.common.TeddyColor;

public record UpdateTeddy(
        int id,
        String name,
        TeddyColor color
) {}
