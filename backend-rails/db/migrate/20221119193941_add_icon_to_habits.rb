class AddIconToHabits < ActiveRecord::Migration[7.0]
  def change
    add_column :habits, :icon, :string
  end
end
