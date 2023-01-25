# == Schema Information
#
# Table name: customers
#
#  id                :bigint           not null, primary key
#  name              :string           not null
#  document_number   :string           not null
#  phone             :string           not null
#  state_inscription :string
#  customer_type     :integer          not null
#  user_id           :string           not null
#  created_at        :datetime         not null
#  updated_at        :datetime         not null
#
# Indexes
#
#  index_customers_on_user_id                      (user_id)
#  index_customers_on_user_id_and_document_number  (user_id,document_number) UNIQUE
#  index_customers_on_user_id_and_name             (user_id,name) UNIQUE
#
require 'rails_helper'

RSpec.describe Customer, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
  end

  context 'validations' do
    context 'enums' do
      it { should define_enum_for(:customer_type) }
    end

    context 'presence' do
      it { should validate_presence_of(:name) }
      it { should validate_presence_of(:document_number) }
      it { should validate_presence_of(:phone) }
    end

    context 'uniqueness' do
      subject { create(:customer) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
      it { should validate_uniqueness_of(:document_number).scoped_to(:user_id).case_insensitive }
    end
  end

  context '#validate_person_state_inscription' do
    subject { build(:customer, customer_type:, state_inscription:) }

    context 'when customer has customer_type person' do
      let(:customer_type) { Customer.customer_types[:person] }
      context 'when has state_inscription nil' do
        let(:state_inscription) { nil }
        it { is_expected.to be_valid }
      end

      context 'when has state_inscription blank' do
        let(:state_inscription) { '' }
        it { is_expected.to be_valid }
      end

      context 'when has state_inscription present' do
        let(:state_inscription) { 'something' }
        it { is_expected.to_not be_valid }
        it 'has proper error message' do
          subject.valid?
          expect(subject.errors[:state_inscription]).to eq(['Person should not have state inscription'])
        end
      end
    end

    context 'when customer has customer_type business' do
      let(:customer_type) { Customer.customer_types[:business] }
      context 'when has state_inscription nil' do
        let(:state_inscription) { nil }
        it { is_expected.to be_valid }
      end

      context 'when has state_inscription blank' do
        let(:state_inscription) { '' }
        it { is_expected.to be_valid }
      end

      context 'when has state_inscription present' do
        let(:state_inscription) { 'something' }
        it { is_expected.to be_valid }
      end
    end
  end

  context 'scopes' do
    context '.by_name_like' do
      let!(:customer1) { create(:customer, name: 'abcde') }
      let!(:customer2) { create(:customer, name: 'bcd') }
      let!(:customer3) { create(:customer, name: 'abc') }
      let!(:customer4) { create(:customer, name: 'bcde') }
      context 'when name_like is present' do
        subject { described_class.by_name_like('bcd') }

        it 'returns categories with name like' do
          is_expected.to match_array([customer1, customer2, customer4])
        end
      end
      context 'when name_like is not present' do
        subject { described_class.by_name_like(nil) }

        it 'returns all categories' do
          is_expected.to match_array([customer1, customer2, customer3, customer4])
        end
      end
    end

    context '.by_customer_type' do
      let!(:customer1) { create(:customer, customer_type: Customer.customer_types[:person]) }
      let!(:customer2) { create(:customer, customer_type: Customer.customer_types[:person]) }
      let!(:customer3) { create(:customer, customer_type: Customer.customer_types[:business]) }
      let!(:customer4) { create(:customer, customer_type: Customer.customer_types[:business]) }
      subject { described_class.by_customer_type(customer_type) }

      context 'when customer_type is person' do
        let(:customer_type) { 'person' }

        it 'returns customers with customer_type person' do
          is_expected.to match_array([customer1, customer2])
        end
      end
      context 'when customer_type is business' do
        let(:customer_type) { 'business' }

        it 'returns customers with customer_type business' do
          is_expected.to match_array([customer3, customer4])
        end
      end
      context 'when customer_type is nil' do
        let(:customer_type) { nil }

        it 'returns all customers' do
          is_expected.to match_array([customer1, customer2, customer3, customer4])
        end
      end
    end
  end
end
