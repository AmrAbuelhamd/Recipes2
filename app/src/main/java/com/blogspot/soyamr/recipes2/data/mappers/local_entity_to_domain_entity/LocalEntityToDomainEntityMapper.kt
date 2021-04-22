//package com.zennex.tickets.data.common.mappers.local_entity_to_domain_entity
//
//import com.zennex.tickets.data.common.contracts.LocaleContract
//import com.zennex.tickets.data.local.db.entities.*
//import com.zennex.tickets.domain.entities.enums.*
//import com.zennex.tickets.domain.entities.models.*
//import com.zennex.tickets.domain.entities.models.retail_outlet.RetailOutlet
//import com.zennex.tickets.domain.entities.models.retail_outlet.StandardRetailOutlet
//import com.zennex.tickets.utils.toBoolean
//import java.util.*
//
//class LocalEntityToDomainEntityMapper(private val miscRepository: LocaleContract.MiscRepository) {
//    suspend fun RequestEntity.toDomain() = with(miscRepository) {
//        Request(
//            id,
//            getRetailOutlet(outletId).toDomain(),
//            assignee?.let { getSimpleUser(it).toDomain() },
//            guarantee.toBoolean(),
//            RequestStatus.getById(status),
//            getRequestType(typeId).toDomain(),
//            getDevice(deviceId).toDomain(),
//            getRepairType(repairTypeId).toDomain(),
//            getRepairSubType(repairSubtypeId).toDomain(),
//            RequestPriority.getById(priority),
//            deviceBookingId?.let { getDeviceBooking(it).toDomain() },
//            tmrFioOpen,
//            tmrSignOpen,
//            description ?: "",
//            denialReasonTMR ?: "",
//            denialReasonAdmin ?: "",
//            userId,
//            photos.ids.map { getPhoto(it).toDomain() },
//            tmrFioClosed ?: "",
//            tmrSignClosed ?: "",
//            Date(createdAt.toLong()),
//            Date(updatedAt.toLong()),
//            deviceMovementType.let { DeviceMovementType.getById(it) },
//            deviceMovementSourceId?.let { getRetailOutlet(it).toDomain() },
//            areaMangerId?.let { getSimpleUser(it).toDomain() },
//            coordinatorId?.let { getSimpleUser(it).toDomain() },
//            comments.ids.map { getRequestComment(it).toDomain() },
//            dueDate?.let { Date(it.toLong()) },
//            deletedAt?.let { Date(it.toLong()) },
//            resultPhotos.ids.map { getPhoto(it).toDomain() },
//            receiptPhotos.ids.map { getPhoto(it).toDomain() }
//        )
//    }
//
//    suspend fun RetailOutletEntity.toDomain() = RetailOutlet(
//        id,
//        name,
//        companyId?.let { miscRepository.getCompany(it) }?.toDomain(),
//        channelId?.let { miscRepository.getChannel(it) }?.toDomain(),
//        address,
//        location,
//        code,
//        phoneNumber,
//        miscRepository.getStandardRetailOutletType(typeId).toDomain(),
//        miscRepository.getCity(cityId).toDomain(),
//        locality
//    )
//
//    suspend fun DeviceEntity.toDomain() = with(miscRepository) {
//        Device(
//            id,
//            name,
//            getDeviceModel(modelId).toDomain(),
//            getCity(cityId).toDomain(),
//            inventoryNumber,
//            DeviceCondition.getById(condition)
//        )
//    }
//
//    suspend fun DeviceModelEntity.toDomain() = DeviceModel(
//        id, name, miscRepository.getDeviceModelType(typeId).toDomain()
//    )
//
//    suspend fun RepairSubtypeEntity.toDomain() = RepairSubtype(
//        id,
//        name,
//        miscRepository.getRepairType(repairTypeId).toDomain()
//    )
//
//
//    suspend fun DeviceBookingEntity.toDomain() = DeviceBooking(
//        miscRepository.getDevice(deviceId).toDomain(),
//        Date(createdAt.toLong()),
//        Date(dueDate.toLong()),
//        BookingActive.getById(active)
//    )
//
//
//    suspend fun StandardRetailOutletEntity.toDomain() = StandardRetailOutlet(
//        id,
//        name,
//        code,
//        location,
//        miscRepository.getSimpleCity(cityId).toDomain(),
//        locality
//    )
//
//
//    suspend fun UserEntity.toDomain() = User(
//        id,
//        name,
//        email,
//        UserRole.getById(role),
//        phoneNumber,
//        miscRepository.getCity(cityId).toDomain()
//    )
//
//
//    suspend fun UserCityEntity.toDomain() = UserCity(
//        id,
//        name!!,
//        miscRepository.getRegion(regionId!!).toDomain(),
//        location ?: "",
//        Date(createdAt.toLong()),
//        Date(updatedAt.toLong()),
//        deletedAt?.let { Date(it.toLong()) }
//    )
//
//    suspend fun StandardConsumableTypeEntity.toDomain() = StandardConsumableType(
//        id,
//        name,
//        miscRepository.getMeasurementUnits(unitId).toDomain()
//    )
//
//
//    suspend fun ConsumableEntity.toDomain() = Consumable(
//        id,
//        typeId?.let { miscRepository.getStandardConsumableType(it).toDomain() },
//        requestId,
//        name,
//        units.toFloat(),
//        costPerUnit.toFloat(),
//        miscRepository.getMeasurementUnits(unitId).toDomain()
//    )
//
//    suspend fun PhotoEntity.toDomain() = Photo(
//        id, miscRepository.getShortUser(shortUserId).toDomain(), url, Date(createAt.toLong())
//    )
//
//    suspend fun RequestCommentEntity.toDomain() = RequestComment(
//        id,
//        requestId,
//        miscRepository.getShortUser(shortUserId).toDomain(),
//        message,
//        createdAt,
//        commentPhotos.ids.map { miscRepository.getCommentPhoto(it).toDomain() },
//        updatedAt
//    )
//
//    suspend fun CommentPhotoEntity.toDomain() = CommentPhoto(id, userId, url, createAt, updateAt)
//}