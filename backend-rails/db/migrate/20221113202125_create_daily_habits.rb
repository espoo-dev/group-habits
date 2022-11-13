class CreateDailyHabits < ActiveRecord::Migration[7.0]
  def change
    create_table :daily_habits do |t|
      t.date :date
      t.boolean :check
      t.references :habit, null: false, foreign_key: true

      t.timestamps
    end
  end
end
