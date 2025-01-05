package com.campasklad.facility.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum WriteoffReason {
    DAMAGED("Повреждено"), // Повреждение товара
    DEFECTIVE("Брак"), // Бракованный товар
    OUTDATED("Устаревший стиль"), // Морально устаревший товар
    UNSOLD("Непроданные остатки"), // Остатки, не проданные за сезон
    LOST("Утеряно"), // Потеря товара
    RETURNED("Возвращено поставщику"), // Возврат поставщику
    OTHER("Другая причина"); // Любая другая причина

    String description;
}