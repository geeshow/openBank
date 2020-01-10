package com.ken207.openbank.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ken207.openbank.domain.Member;

import java.io.IOException;

public class MemberSerializer extends JsonSerializer<Member> {
    @Override
    public void serialize(Member member, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", member.getId());
        gen.writeEndObject();
    }
}
