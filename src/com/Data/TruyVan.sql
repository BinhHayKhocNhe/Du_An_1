use Du_An_1;
go

CREATE TRIGGER PreventUpdateScheduleSchoolDay
ON Schedule
INSTEAD OF UPDATE
AS
BEGIN
    IF UPDATE(School_Day)
    BEGIN
        IF EXISTS (
            SELECT 1
            FROM inserted i
            WHERE i.School_Day = CONVERT(DATE, GETDATE()) -- Kiểm tra xem ngày hiện tại đã có trong bảng inserted hay không
        )
        BEGIN
            THROW 50000, 'Không thể cập nhật School_Day khi ngày hiện tại đã tồn tại trong bảng.', 1;
        END
        ELSE
        BEGIN
            -- Nếu điều kiện không đúng, tiến hành cập nhật bình thường
            UPDATE s
            SET s.School_Day = i.School_Day
            FROM Schedule s
            INNER JOIN inserted i ON s.ID_Course = i.ID_Course; -- Thay thế 'ID_Course' bằng tên cột khóa chính thực tế của bảng
        END
    END
END;



